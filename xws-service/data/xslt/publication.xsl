<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nr="http://www.ftn.uns.ac.rs/xws/tim5"
                version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Publication</title>
                <style type="text/css">
                    body {
                        margin: 0;
                        font-family: sans-serif;
                    }
                </style>
            </head>
            <body>
                <h1><xsl:value-of select="//nr:NaslovnaStrana/nr:Naslov" /></h1>

                <p>
                    <i><b>Abstract.</b></i>
                    <xsl:value-of select="//nr:Abstrakt" />
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
