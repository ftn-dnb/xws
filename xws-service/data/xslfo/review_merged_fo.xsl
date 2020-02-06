<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rec="http://www.ftn.uns.ac.rs/xws/tim5"
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
                    <xsl:for-each select="//rec:Recenzija">
                        <xsl:variable name="reviewCount" select="position()"/>

                        <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
                            Review #<xsl:value-of select="$reviewCount" />
                        </fo:block>

                        <fo:block font-family="sans-serif" font-size="16px" font-weight="bold" padding="5px">
                            Status: <xsl:value-of select="//rec:Recenzija[position()=$reviewCount]/rec:Preporuka" />
                        </fo:block>

                        <xsl:for-each select="//rec:Recenzija[position()=$reviewCount]/rec:Komentar">
                            <xsl:variable name="commentCount" select="position()" />

                            <fo:block text-indent="10px" font-family="sans-serif" font-size="16px" font-weight="bold" padding="5px">
                                Comment #<xsl:value-of select="$commentCount" />
                            </fo:block>

                            <fo:block text-indent="15px" font-family="sans-serif" font-size="12px" padding="5px">
                                <fo:inline font-weight="bold">
                                    Element from publication:
                                </fo:inline>
                                <fo:inline >
                                    <xsl:value-of select="//rec:Recenzija[position()=$reviewCount]/rec:Komentar[position()=$commentCount]/rec:ReferenciraniElement" />
                                </fo:inline>
                            </fo:block>

                            <fo:block text-indent="15px" font-family="sans-serif" font-size="12px" padding="5px">
                                <fo:inline font-weight="bold">
                                    Comment:
                                </fo:inline>
                                <fo:inline >
                                    <xsl:value-of select="//rec:Recenzija[position()=$reviewCount]/rec:Komentar[position()=$commentCount]/rec:KomentariAutor" />
                                </fo:inline>
                            </fo:block>
                        </xsl:for-each>
                    </xsl:for-each>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
