<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Students</h2>
                <table border = "1">
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Faculty</th>
                    <th>Department</th>
                    <th>Average</th>
                    <xsl:for-each select="/Students/child::Student">
                        <tr>
                            <td><xsl:value-of select="Name"/></td>
                            <td><xsl:value-of select="Surname"/></td>
                            <td><xsl:value-of select="Faculty"/></td>
                            <td><xsl:value-of select="Department"/></td>
                            <td><xsl:value-of select="AveragePoint"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>