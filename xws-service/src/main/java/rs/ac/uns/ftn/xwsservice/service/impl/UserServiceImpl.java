package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.common.TimeProvider;
import rs.ac.uns.ftn.xwsservice.common.consts.UserRole;
import rs.ac.uns.ftn.xwsservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.mappers.UserMapper;
import rs.ac.uns.ftn.xwsservice.model.ConfirmationToken;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.AuthorityRepository;
import rs.ac.uns.ftn.xwsservice.repository.ConfirmationTokenRepository;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.MailSenderService;
import rs.ac.uns.ftn.xwsservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TimeProvider timeProvider;


    @Override
    public User addUser(UserRegistrationDTO userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()) != null) {
            throw new ApiRequestException("Username '" + userInfo.getUsername() + "' already exists.");
        }

        if (!userInfo.getPassword().equals(userInfo.getRepeatPassword())) {
            throw new ApiRequestException("Provided passwords must be the same.");
        }

        if (userRepository.findByEmail(userInfo.getEmail()) != null) {
            throw new ApiRequestException("Email '" + userInfo.getEmail() + "' is taken.");
        }

        User user = createNewUserObject(userInfo);
        userRepository.save(user);

        ConfirmationToken token = new ConfirmationToken(user);
        tokenRepository.save(token);

        mailSenderService.sendRegistrationMail(token);

        return user;
    }

    private User createNewUserObject(UserRegistrationDTO userInfo) {
        User user = UserMapper.toEntity(userInfo);
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        user.setLastPasswordResetDate(timeProvider.nowTimestamp());
        user.getUserAuthorities().add(authorityRepository.findByName(UserRole.ROLE_AUTHOR));

        return user;
    }

    @Override
    public boolean activateAccount(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);

        if (confirmationToken == null) {
            throw new ResourceNotFoundException("Confirmation token doesn't exist.");
        }

        if (confirmationToken.isUsed()) {
            throw new ApiRequestException("This token has been already used.");
        }

        User user = confirmationToken.getUser();
        long timeDifference = timeProvider.timeDifferenceInMinutes(timeProvider.now(), confirmationToken.getDatetimeCreated());

        if (timeDifference < 30) {
            user.setActivatedAccount(true);
            userRepository.save(user);
            confirmationToken.setUsed(true);
            tokenRepository.save(confirmationToken);
            mailSenderService.sendAccountActivatedMail(user);
            return true;
        } else {
            tokenRepository.delete(confirmationToken);
            userRepository.delete(user);
            throw new ApiRequestException("Confirmation token timed out.");
        }
    }

}
