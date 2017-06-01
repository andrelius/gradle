package Gradle_Check_TestCoverageCrossVersionFullJava7Windows.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*

object Gradle_Check_TestCoverageCrossVersionFullJava7Windows_1 : BuildType({
    template(Gradle_Check.buildTypes.Gradle_Check_TestCoverageForkedWindows)
    uuid = "0456fd2f-8f82-410d-bc94-32e5c04460bd"
    extId = "Gradle_Check_TestCoverageCrossVersionFullJava7Windows_1"
    name = "Test Coverage - Cross-version Full Java7 Windows"

    params {
        param("env.JAVA_HOME", "%windows.java7.oracle.64bit%")
        param("org.gradle.test.bucket", "1")
        param("org.gradle.test.buildType", "crossVersion")
        param("webhook.body", """
            {
            "text":" ${'$'}{buildResult} - *${'$'}{buildName}* <${'$'}{buildStatusUrl}|#${'$'}{buildNumber}> (triggered by ${'$'}{triggeredBy})"
            }
        """.trimIndent())
    }
})