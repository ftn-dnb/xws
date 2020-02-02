<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nr ="http://www.ftn.uns.ac.rs/xws/tim5"
				xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
                xmlns:sh="https://schema.org/">

    <xsl:output method="xml" indent="yes"/>
	
	
	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="nr:NaucniRad">
		<NaucniRad vocab="https://schema.org/ScholarlyArticle"
				   about="http://www.ftn.uns.ac.rs/xws/tim5/naucni-radovi/{@id}"
				   typeof="sh:ScolarlyArticle">
			<xsl:apply-templates select="node()|@*"/>
		</NaucniRad>
	</xsl:template>
	
	
	<xsl:template match="nr:NaucniRad/nr:NaslovnaStrana/nr:Naslov">
		<Naslov property="sh:headline" datatype="sh:Text" content="{.}">
			<xsl:apply-templates select="node()|@*"/>
		</Naslov>
	</xsl:template>
	
	<xsl:template match="//nr:DatumPrijema">
		<DatumPrijema property="sh:dateCreated" datatype="xsd:date" content="{.}">
			 <xsl:apply-templates select="node()|@*"/>
		</DatumPrijema>
	</xsl:template>
	
	<xsl:template match="//nr:DatumPrihvatanja">
		<DatumPrihvatanja property="sh:datePublished" datatype="xsd:date" content="{.}">
			 <xsl:apply-templates select="node()|@*"/>
		</DatumPrihvatanja>
	</xsl:template>
	
	<xsl:template match="//nr:TipRada">
		<TipRada property="sh:genre" datatype="sh:Text" content="{.}">
			 <xsl:apply-templates select="node()|@*"/>
		</TipRada>
	</xsl:template>
	
	<xsl:template match="//nr:VerzijaRada">
		<VerzijaRada property="sh:version" datatype="sh:Number" content="{.}">
			 <xsl:apply-templates select="node()|@*"/>
		</VerzijaRada>
	</xsl:template>
	
	<xsl:template match="//nr:Jezik">
		<Jezik property="sh:inLanguage" datatype="sh:Text" content="{.}">
			 <xsl:apply-templates select="node()|@*"/>
		</Jezik>
	</xsl:template>
	
	<xsl:variable name="KljucneReci">
		<xsl:for-each select="//nr:KljucneReci/nr:KljucnaRec">
			<xsl:if test="position() != 1">,</xsl:if>
			<xsl:value-of select="."/>
		</xsl:for-each>
	</xsl:variable>

	<xsl:template match="//nr:KljucneReci">
		<KljucneReci property="sh:keywords" datatype="sh:Text" content="{$KljucneReci}">
			 <xsl:apply-templates select="node()|@*"/>
		</KljucneReci>
	</xsl:template>

	<xsl:variable name="Autori">
		<xsl:for-each select="//nr:NaslovnaStrana/nr:Autori/nr:Autor">
			<xsl:if test="position() != 1">,</xsl:if>
			<xsl:value-of select="nr:Ime"/>
			<xsl:value-of select="nr:Prezime"/>
		</xsl:for-each>
	</xsl:variable>

	<xsl:template match="//nr:Autori">
		<Autori property="sh:additionalName" datatype="sh:Text" content="{$Autori}">
			<xsl:apply-templates select="node()|@*"/>
		</Autori>
	</xsl:template>

</xsl:stylesheet>
