<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
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
                    <h3><xsl:value-of select="//Datum" /></h3>
                </div>

                <div class="people-info">
                    <div class="sender">
                        <h2>Sender</h2>
                        <p><strong>First name:</strong> <xsl:value-of select="//Posaljilac/Ime" /></p>
                        <p><strong>Last name:</strong> <xsl:value-of select="//Posaljilac/Prezime" /></p>
                        <p><strong>E-Mail:</strong> <xsl:value-of select="//Posaljilac/EMail" /></p>
                        <p><strong>Phone number:</strong> <xsl:value-of select="//Posaljilac/BrojTelefona" /></p>
                        <p><strong>ORCID:</strong> <xsl:value-of select="//Posaljilac/ORCID" /></p>
                        <p><strong>Address:</strong>
                            <xsl:value-of select="//Posaljilac/Ustanova/Naziv" />,
                            <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Ulica" />&#160;
                            <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Broj" />,
                            <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Grad" />,
                            <xsl:value-of select="//Posaljilac/Ustanova/Adresa/Drzava" />
                        </p>
                    </div>

                    <div class="receiver">
                        <h2>Recipient</h2>
                        <p><strong>First name:</strong> <xsl:value-of select="//Primalac/Ime" /></p>
                        <p><strong>Last name:</strong> <xsl:value-of select="//Primalac/Prezime" /></p>
                        <p><strong>E-Mail:</strong> <xsl:value-of select="//Primalac/EMail" /></p>
                        <p><strong>Phone number:</strong> <xsl:value-of select="//Primalac/BrojTelefona" /></p>
                        <p><strong>ORCID:</strong> <xsl:value-of select="//Primalac/ORCID" /></p>
                        <p><strong>Address:</strong>
                            <xsl:value-of select="//Primalac/Ustanova/Naziv" />,
                            <xsl:value-of select="//Primalac/Ustanova/Adresa/Ulica" />&#160;
                            <xsl:value-of select="//Primalac/Ustanova/Adresa/Broj" />,
                            <xsl:value-of select="//Primalac/Ustanova/Adresa/Grad" />,
                            <xsl:value-of select="//Primalac/Ustanova/Adresa/Drzava" />
                        </p>
                    </div>
                </div>

                <div class="content">
                    <p>
                        <xsl:value-of select="//SadrzajPisma" />
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
