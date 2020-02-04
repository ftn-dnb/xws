<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nr="http://www.ftn.uns.ac.rs/xws/tim5"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Tabela">
        <fo:block margin-top="20px" margin-bottom="20px">
            <fo:table >
                <fo:table-body>
                    <xsl:for-each select="nr:Red">
                        <fo:table-row border-width="4px" border-style="solid">
                            <xsl:for-each select="nr:Celija">
                                <fo:table-cell border-width="4px" border-style="solid"  ><fo:block text-align="center" ><xsl:value-of select="."/></fo:block></fo:table-cell>
                            </xsl:for-each>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Slika">
        <fo:block-container height="210px" margin-top="10px" margin-bottom="10px" text-align="center">
            <fo:block>
                <xsl:element name="fo:external-graphic">
                    <xsl:attribute name="src">data:image/png;base64, <xsl:value-of select="nr:Sadrzaj"/>
                    </xsl:attribute>
                    <xsl:attribute name="content-height">200</xsl:attribute>
                </xsl:element>
            </fo:block>
            <fo:block><xsl:value-of select="nr:Naziv"/> </fo:block>
        </fo:block-container>
    </xsl:template>




    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Lista">
        <xsl:choose>
            <xsl:when test="@Vrsta='uredjena'">
                <fo:block margin-top="20px" margin-bottom="20px">
                    <fo:list-block>
                        <xsl:for-each select="nr:Stavka">
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block margin-right="10px"><xsl:value-of select="position()"/> </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="10px"><xsl:value-of select="."/></fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </xsl:for-each>
                    </fo:list-block>
                </fo:block>
            </xsl:when>
            <xsl:otherwise>
                <fo:block margin-top="20px" margin-bottom="20px">
                    <fo:list-block>
                        <xsl:for-each select="nr:Stavka">
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block margin-right="5px">*</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="5px"><xsl:value-of select="."/></fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </xsl:for-each>
                    </fo:list-block>
                </fo:block>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Pasus/nr:Tekst">
        <fo:block margin-bottom="20px">
            <xsl:value-of select="nr:Sadrzaj"/>
            <xsl:if test="nr:IdReference != ''">[<xsl:value-of select="nr:IdReference"/>]</xsl:if>
        </fo:block>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Tabela">
        <fo:block margin-top="20px" margin-bottom="20px">
            <fo:table>
                <fo:table-body>
                    <xsl:for-each select="nr:Red">
                        <fo:table-row border-width="4px" border-style="solid">
                            <xsl:for-each select="nr:Celija">
                                <fo:table-cell border-width="4px" border-style="solid"  ><fo:block text-align="center" ><xsl:value-of select="."/></fo:block></fo:table-cell>
                            </xsl:for-each>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Slika">
        <fo:block-container height="210px" margin-top="10px" margin-bottom="10px" text-align="center">
            <fo:block>
                <xsl:element name="fo:external-graphic">
                    <xsl:attribute name="src">data:image/png;base64, <xsl:value-of select="nr:Sadrzaj"/>
                    </xsl:attribute>
                    <xsl:attribute name="content-height">200</xsl:attribute>
                </xsl:element>
            </fo:block>
            <fo:block><xsl:value-of select="nr:Naziv"/> </fo:block>
        </fo:block-container>
    </xsl:template>



    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Lista">
        <xsl:choose>
            <xsl:when test="@Vrsta='uredjena'">
                <fo:block margin-top="20px" margin-bottom="20px">
                    <fo:list-block>
                        <xsl:for-each select="nr:Stavka">
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block margin-right="10px"><xsl:value-of select="position()"/> </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="10px"><xsl:value-of select="."/></fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </xsl:for-each>
                    </fo:list-block>
                </fo:block>
            </xsl:when>
            <xsl:otherwise>
                <fo:block margin-top="20px" margin-bottom="20px">
                    <fo:list-block>
                        <xsl:for-each select="nr:Stavka">
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block margin-right="5px">*</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="5px"><xsl:value-of select="."/></fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </xsl:for-each>
                    </fo:list-block>
                </fo:block>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje/nr:Pasus/nr:Tekst">
        <fo:block  margin-bottom="20px">
            <xsl:value-of select="nr:Sadrzaj"/>
            <xsl:if test="nr:IdReference != ''">[<xsl:value-of select="nr:IdReference"/>]</xsl:if>
        </fo:block>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Poglavlje">
        <fo:block margin-top="20px" margin-bottom="20px" font-size="15px" ><xsl:value-of select="nr:Naslov"/></fo:block>
        <xsl:for-each select="nr:Pasus">
            <fo:block><xsl:apply-templates select="."/></fo:block>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje/nr:Naslov">
        <fo:block margin-top="20px" margin-bottom="20px" font-size="20px"><xsl:value-of select="."/></fo:block>
    </xsl:template>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="letter-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="letter-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        <xsl:value-of select="//nr:NaslovnaStrana/nr:Naslov" />
                    </fo:block>


                    <fo:block>
                        Abstract:
                        <fo:inline font-style="italic" margin-left="100px" margin-right="100px">
                            <xsl:value-of select="//nr:Abstrakt" />
                        </fo:inline>
                    </fo:block>

                    <fo:block margin-top="20" margin-bottom="20">
                        Keywords:
                        <fo:inline font-weight="bold">
                            <xsl:for-each select="nr:NaucniRad/nr:KljucneReci/nr:KljucnaRec">
                                <xsl:if test="position() != 1">,</xsl:if>
                                <xsl:value-of select="."/>
                            </xsl:for-each>
                        </fo:inline>
                    </fo:block>

                    <fo:block margin-bottom="20px" margin-top="20">
                        <xsl:for-each select="nr:NaucniRad/nr:Poglavlja/nr:Poglavlje">
                            <fo:block>
                                <xsl:apply-templates select="."/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>

                    <fo:block>
                        <fo:block font-size="20px">References</fo:block>
                        <fo:list-block>
                            <xsl:for-each select="nr:NaucniRad/nr:Reference/nr:Referenca">
                                <fo:list-item>
                                    <fo:list-item-label>
                                        <fo:block margin-right="10px"><xsl:value-of select="position()"/> </fo:block>
                                    </fo:list-item-label>
                                    <fo:list-item-body>
                                        <fo:block margin-left="10px">
                                            <xsl:value-of select="nr:NazivRada"/>
                                            (<xsl:for-each select="nr:Autor">
                                            <xsl:if test="position() != 1">,</xsl:if>
                                            <xsl:value-of select="."/>
                                        </xsl:for-each>)
                                            <xsl:element name="fo:basic-link">
                                                <xsl:attribute name="external-destination">url('<xsl:value-of select="nr:URLRada"/>')
                                                </xsl:attribute>
                                                <xsl:attribute name="color">blue</xsl:attribute>link
                                            </xsl:element>
                                        </fo:block>
                                    </fo:list-item-body>
                                </fo:list-item>
                            </xsl:for-each>
                        </fo:list-block>
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
