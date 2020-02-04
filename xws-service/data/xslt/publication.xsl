<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nr="http://www.ftn.uns.ac.rs/xws/tim5"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                version="2.0">




    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Tabela">
    <table border="1">
        <xsl:for-each select="nr:Red">
            <tr>
                <xsl:for-each select="nr:Celija">
                    <td>
                        <xsl:value-of select="."/>
                    </td>
                </xsl:for-each>
            </tr>
        </xsl:for-each>
    </table>
</xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Slika">
        <div class="slika">
            <xsl:element name="img">
                <xsl:attribute name="src">data:image/png;base64, <xsl:value-of select="nr:Sadrzaj"/>
                </xsl:attribute>
            </xsl:element>
            <p><xsl:value-of select="nr:Naziv"/></p>
        </div>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Lista">
        <xsl:choose>
            <xsl:when test="@Vrsta='uredjena'">
                <ol>
                    <xsl:for-each select="nr:Stavka">
                        <li><xsl:value-of select="."/></li>
                    </xsl:for-each>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ul>
                    <xsl:for-each select="nr:Stavka">
                        <li><xsl:value-of select="."/></li>
                    </xsl:for-each>
                </ul>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Tabela">
        <table border="1">
            <xsl:for-each select="nr:Red">
                <tr>
                    <xsl:for-each select="nr:Celija">
                        <td>
                            <xsl:value-of select="."/>
                        </td>
                    </xsl:for-each>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Slika">
        <div class="slika">
            <xsl:element name="img">
                <xsl:attribute name="src">data:image/png;base64, <xsl:value-of select="nr:Sadrzaj"/>
                </xsl:attribute>
            </xsl:element>
            <p><xsl:value-of select="nr:Naziv"/></p>
        </div>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Lista">
        <xsl:choose>
            <xsl:when test="@Vrsta='uredjena'">
                <ol>
                    <xsl:for-each select="nr:Stavka">
                        <li><xsl:value-of select="."/></li>
                    </xsl:for-each>
                </ol>
            </xsl:when>
            <xsl:otherwise>
                <ul>
                    <xsl:for-each select="nr:Stavka">
                        <li><xsl:value-of select="."/></li>
                    </xsl:for-each>
                </ul>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje">
        <h3><xsl:value-of select="nr:Naslov"/></h3>
        <xsl:for-each select="nr:Pasus">
            <p><xsl:apply-templates select="."/></p>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Tekst">
        <xsl:value-of select="nr:Sadrzaj"/>
        <xsl:if test="nr:IdReference != ''">[<xsl:value-of select="nr:IdReference"/>]</xsl:if>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Tekst">
        <xsl:value-of select="nr:Sadrzaj"/>
        <xsl:if test="nr:IdReference != ''">[<xsl:value-of select="nr:IdReference"/>]</xsl:if>
    </xsl:template>

    <xsl:template match="/">
        <html>
            <head>
                <title>Publication</title>
                <style type="text/css">
                    body {
                        margin-top:20px;
                        margin-bottom:20px;
                        font-family: comic-sans;
                        margin-left:100px;
                        margin-right:100px;
                    }
                    table{
                        text-align:center;
                        margin-left:auto;
                        margin-right:auto;
                    }
                    h1 {
                        text-align:center;
                    }
                    .slika img{
                        display: block;
                        margin-left:auto;
                        margin-right:auto;
                    }
                    .slika p {
                        text-align:center;
                    }
                </style>
            </head>
            <body>
                <h1><xsl:value-of select="//nr:NaslovnaStrana/nr:Naslov" /></h1>

                <table border="1">
                    <tr>
                        <xsl:for-each select="nr:NaucniRad/nr:NaslovnaStrana/nr:Autori/nr:Autor">
                            <td>
                                <table border="1">
                                    <tr><td><xsl:value-of select="nr:Ime"/> <xsl:text> </xsl:text></td></tr>
                                    <tr><td><xsl:value-of select="nr:Prezime"/></td></tr>
                                    <tr><td><xsl:value-of select="nr:EMail"/></td> </tr>
                                    <tr><td><xsl:value-of select="nr:Ustanova/nr:Naziv"/> </td></tr>
                                    <tr><td>ORCID:<xsl:value-of select="nr:ORCID"/></td> </tr>
                                </table>
                            </td>
                        </xsl:for-each>
                    </tr>
                </table>

                <p>
                    <i><b>Abstract:</b></i>
                    <xsl:value-of select="//nr:Abstrakt" />
                </p>
                <p>
                    Keywords:
                    <xsl:for-each select="nr:NaucniRad/nr:KljucneReci/nr:KljucnaRec">
                        <xsl:if test="position() != 1">,</xsl:if>
                        <xsl:value-of select="."/>
                    </xsl:for-each>
                </p>

                <xsl:for-each select="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje">
                    <h2><xsl:value-of select="nr:Naslov"/></h2>
                    <p><xsl:apply-templates select="."/> </p>
                </xsl:for-each>

                <h2>References</h2>
                <ol>
                    <xsl:for-each select="nr:NaucniRad/nr:Reference/nr:Referenca">
                        <li><xsl:value-of select="nr:NazivRada"/>
                            (<xsl:for-each select="nr:Autor">
                                <xsl:if test="position() != 1">,</xsl:if>
                                <xsl:value-of select="."/>
                            </xsl:for-each>)
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    <xsl:value-of select="nr:URLRada"/>
                                </xsl:attribute>link</xsl:element></li>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
