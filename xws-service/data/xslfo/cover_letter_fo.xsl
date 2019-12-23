<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="letter-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="letter-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
                        Cover letter
                    </fo:block>

                    <fo:block text-indent="10px">
                        Date:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//Datum" />
                        </fo:inline>
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="16px" font-weight="bold" padding="10px">
                        Sender
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">First name: </fo:inline>
                        <xsl:value-of select="//Posaljilac/Ime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Last name: </fo:inline>
                        <xsl:value-of select="//Posaljilac/Prezime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">E-Mail: </fo:inline>
                        <xsl:value-of select="//Posaljilac/EMail" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Phone number: </fo:inline>
                        <xsl:value-of select="//Posaljilac/BrojTelefona" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">ORCID: </fo:inline>
                        <xsl:value-of select="//Posaljilac/ORCID" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Address: </fo:inline>
                        <xsl:value-of select="//Posaljilac/Ustanova/Naziv" />,
                        <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Ulica" />
                        <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Broj" />,
                        <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Grad" />,
                        <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Drzava" />
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="16px" font-weight="bold" padding="10px">
                        Recipient
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">First name: </fo:inline>
                        <xsl:value-of select="//Primalac/Ime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Last name: </fo:inline>
                        <xsl:value-of select="//Primalac/Prezime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">E-Mail: </fo:inline>
                        <xsl:value-of select="//Primalac/EMail" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Phone number: </fo:inline>
                        <xsl:value-of select="//Primalac/BrojTelefona" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">ORCID: </fo:inline>
                        <xsl:value-of select="//Primalac/ORCID" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Address: </fo:inline>
                        <xsl:value-of select="//Primalac/Ustanova/Naziv" />,
                        <xsl:value-of select="//Primalac/Ustanova/Adresa/Ulica" />
                        <xsl:value-of select="//Primalac/Ustanova/Adresa/Broj" />,
                        <xsl:value-of select="//Primalac/Ustanova/Adresa/Grad" />,
                        <xsl:value-of select="//Primalac/Ustanova/Adresa/Drzava" />
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="18px" font-weight="bold" padding="10px">
                        Content:
                    </fo:block>
                    <fo:block text-indent="10px">
                        <xsl:value-of select="//SadrzajPisma" />
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
