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
                    <fo:block font-family="sans-serif" font-size="18px" font-weight="bold" padding="10px">
                        Anonimna verzija naucnog rada
                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
                        <xsl:value-of select="//nr:NaslovnaStrana/nr:Naslov" />
                    </fo:block>

                    <fo:block text-indent="10px">
                        Abstract:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//nr:Abstrakt" />
                        </fo:inline>
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
