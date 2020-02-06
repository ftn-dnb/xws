<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rec="http://www.ftn.uns.ac.rs/xws/tim5"
                version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Reviews merged</title>
                <style>
                    body {
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <h1>Review</h1>
                <h3>Status: <xsl:value-of select="//rec:Recenzija/rec:Preporuka" /></h3>

                <xsl:for-each select="//rec:Recenzija/rec:Komentar">
                    <xsl:variable name="commentCount" select="position()" />

                    <h4>Comment #<xsl:value-of select="$commentCount" /></h4>

                    <strong>Element from publication: </strong>
                    <xsl:value-of select="//rec:Recenzija/rec:Komentar[position()=$commentCount]/rec:ReferenciraniElement" />

                    <br />

                    <strong>Comment: </strong>
                    <xsl:value-of select="//rec:Recenzija/rec:Komentar[position()=$commentCount]/rec:KomentariAutor" />

                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
