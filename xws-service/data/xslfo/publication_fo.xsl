<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nr="http://www.ftn.uns.ac.rs/xws/tim5"
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
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        <xsl:value-of select="//nr:NaslovnaStrana/nr:Naslov" />
                    </fo:block>


                    <fo:block text-align="center" margin-top="20px"
                                                  margin-bottom="20px">
                        <fo:table>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:for-each select="nr:NaucniRad/nr:NaslovnaStrana/nr:Autori/nr:Autor">
                                        <fo:table-cell>
                                            <fo:block>
                                            <fo:table>
                                                <fo:table-body>
                                                    <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="nr:Ime"/></fo:block></fo:table-cell></fo:table-row>
                                                    <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="nr:Prezime"/></fo:block></fo:table-cell></fo:table-row>
                                                    <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="nr:EMail"/></fo:block></fo:table-cell></fo:table-row>
                                                    <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="nr:Ustanova/nr:Naziv"/></fo:block></fo:table-cell></fo:table-row>
                                                    <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="nr:ORCID"/></fo:block></fo:table-cell></fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            </fo:block>
                                        </fo:table-cell>

                                    </xsl:for-each>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block>
                        Abstract:
                        <fo:inline font-style="italic" margin-left="100px" margin-right="100px">
                            <xsl:value-of select="//nr:Abstrakt" />
                        </fo:inline>
                    </fo:block>

                    <fo:block margin-top="20" margin-bottom="20">
                        Keywords:
                        <fo:inline>
                            <xsl:for-each select="nr:NaucniRad/nr:KljucneReci/nr:KljucnaRec">
                                <xsl:if test="position() != 1">,</xsl:if>
                                <xsl:value-of select="."/>
                            </xsl:for-each>
                        </fo:inline>
                    </fo:block>

                    <fo:block>
                        <xsl:for-each select="nr:NaucniRad/nr:Poglavlja">
                            <fo:block>
                                <xsl:value-of select="nr:Poglavlje/nr:Naslov"/>
                                TEKST
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
