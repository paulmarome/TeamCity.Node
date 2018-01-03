package TeamCityNodePlugin.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2017_2.ideaRunner
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with uuid = '576e3f87-4778-4e9f-8b27-f351ac47dc1d' (id = 'TeamCityNodePlugin_TeamCityNodeGradle')
accordingly and delete the patch script.
*/
changeBuildType("576e3f87-4778-4e9f-8b27-f351ac47dc1d") {
    expectSteps {
        step {
            type = "kotlinc"
            param("KOTLIN_TAG", "1.0.2")
        }
        ideaRunner {
            pathToProject = ""
            jdk {
                name = "1.6"
                path = "%env.JDK_16%"
                patterns("jre/lib/*.jar")
                extAnnotationPatterns("%teamcity.tool.idea%/lib/jdkAnnotations.jar")
            }
            pathvars {
                variable("TeamCityDistribution", "%system.path.macro.TeamCityDistribution%")
            }
            jvmArgs = "-Xmx256m"
            targetJdkHome = "%env.JDK_18_x64%"
            runConfigurations = "All Tests"
            artifactsToBuild = "plugin-zip"
        }
        gradle {
            tasks = "teamcity"
            buildFile = "build.gradle.kts"
            useGradleWrapper = true
            jdkHome = "%env.JDK_18_x64%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }
    steps {
        items.removeAt(0)
        items.removeAt(0)
        update<GradleBuildStep>(0) {
        }
    }

    dependencies {
        remove("TeamCityNodePlugin_TeamCityNodeVs100x") {
            snapshot {
                reuseBuilds = ReuseBuilds.ANY
                onDependencyCancel = FailureAction.ADD_PROBLEM
            }
        }

        remove("TeamCityNodePlugin_TeamCityNodeVs90x") {
            snapshot {
                reuseBuilds = ReuseBuilds.ANY
                onDependencyCancel = FailureAction.ADD_PROBLEM
            }
        }

        remove("TeamCityNodePlugin_TeamCityNodeVs91x") {
            snapshot {
                reuseBuilds = ReuseBuilds.ANY
                onDependencyCancel = FailureAction.ADD_PROBLEM
            }
        }

        remove("JetBrainsDependencyRetrieve_TeamCityTrunkEapReleases") {
            artifacts {
                buildRule = lastSuccessful()
                cleanDestination = true
                artifactRules = "TeamCity-*.tar.gz!TeamCity/** => %teamcity.dist%"
            }
        }

    }
}
