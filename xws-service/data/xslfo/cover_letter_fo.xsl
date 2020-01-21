<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:pp="http://www.ftn.uns.ac.rs/xws/tim5"
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
                            <xsl:value-of select="//pp:Datum" />
                        </fo:inline>
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="16px" font-weight="bold" padding="10px">
                        Sender
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">First name: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:Ime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Last name: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:Prezime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">E-Mail: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:EMail" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Phone number: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:BrojTelefona" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">ORCID: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:ORCID" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Address: </fo:inline>
                        <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Naziv" />,
                        <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Ulica" />
                        <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Broj" />,
                        <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Grad" />,
                        <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Drzava" />
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="16px" font-weight="bold" padding="10px">
                        Recipient
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">First name: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:Ime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Last name: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:Prezime" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">E-Mail: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:EMail" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Phone number: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:BrojTelefona" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">ORCID: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:ORCID" />
                    </fo:block>
                    <fo:block text-indent="10px">
                        <fo:inline font-weight="bold">Address: </fo:inline>
                        <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Naziv" />,
                        <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Ulica" />
                        <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Broj" />,
                        <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Grad" />,
                        <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Drzava" />
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="18px" font-weight="bold" padding="10px">
                        Content:
                    </fo:block>
                    <fo:block text-indent="10px">
                        <xsl:value-of select="//pp:SadrzajPisma" />
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
