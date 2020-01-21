<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:pp="http://www.ftn.uns.ac.rs/xws/tim5"
                version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Cover letter</title>
                <style type="text/css">
                    body {
                        margin: 0;
                        font-family: sans-serif;
                    }

                    .header {
                        background-color: #ffbd80;
                        text-align: center;
                        padding: 1px;
                    }

                    .people-info {
                        display: flex;
                        background-color: #ffd2a8;
                        justify-content: center;
                    }

                    .people-info p {
                        margin: 3px;
                    }

                    .sender {
                        margin-right: 30px;
                    }

                    .receiver {
                        margin-left: 30px;
                    }

                    .content {
                        margin-top: 30px;
                        margin-left: 100px;
                        margin-right: 100px;
                    }
                </style>
            </head>
            <body>
                <div class="header">
                    <h1>Cover letter</h1>
                    <h3><xsl:value-of select="//pp:Datum" /></h3>
                </div>

                <div class="people-info">
                    <div class="sender">
                        <h2>Sender</h2>
                        <p><strong>First name:</strong> <xsl:value-of select="//pp:Posaljilac/pp:Ime" /></p>
                        <p><strong>Last name:</strong> <xsl:value-of select="//pp:Posaljilac/pp:Prezime" /></p>
                        <p><strong>E-Mail:</strong> <xsl:value-of select="//pp:Posaljilac/pp:EMail" /></p>
                        <p><strong>Phone number:</strong> <xsl:value-of select="//pp:Posaljilac/pp:BrojTelefona" /></p>
                        <p><strong>ORCID:</strong> <xsl:value-of select="//pp:Posaljilac/pp:ORCID" /></p>
                        <p><strong>Address:</strong>
                            <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Naziv" />,
                            <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Ulica" />&#160;
                            <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Broj" />,
                            <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Grad" />,
                            <xsl:value-of select="//pp:Posaljilac/pp:Ustanova/pp:Adresa/pp:Drzava" />
                        </p>
                    </div>

                    <div class="receiver">
                        <h2>Recipient</h2>
                        <p><strong>First name:</strong> <xsl:value-of select="//pp:Primalac/pp:Ime" /></p>
                        <p><strong>Last name:</strong> <xsl:value-of select="//pp:Primalac/pp:Prezime" /></p>
                        <p><strong>E-Mail:</strong> <xsl:value-of select="//pp:Primalac/pp:EMail" /></p>
                        <p><strong>Phone number:</strong> <xsl:value-of select="//pp:Primalac/pp:BrojTelefona" /></p>
                        <p><strong>ORCID:</strong> <xsl:value-of select="//pp:Primalac/pp:ORCID" /></p>
                        <p><strong>Address:</strong>
                            <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Naziv" />,
                            <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Ulica" />&#160;
                            <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Broj" />,
                            <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Grad" />,
                            <xsl:value-of select="//pp:Primalac/pp:Ustanova/pp:Adresa/pp:Drzava" />
                        </p>
                    </div>
                </div>

                <div class="content">
                    <p>
                        <xsl:value-of select="//pp:SadrzajPisma" />
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
