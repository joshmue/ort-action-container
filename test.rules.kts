/**
 * Just testing
 */

fun PackageRule.LicenseRule.isMIT() =
    object : RuleMatcher {
        override val description = "isMIT($license)"
        override fun matches() = (license.lowercase() == "mit")
    }

fun RuleSet.noMITRule() = packageRule("NO_MIT") {
    require {
        -isExcluded()
    }
    licenseRule("NO_MIT", LicenseView.CONCLUDED_OR_DECLARED_AND_DETECTED) {
        require {
            -isExcluded()
        }
        error(
            "No MIT allowed - for testing." +
            "Concerns package ${pkg.id.toCoordinates()}",
            "Do not do anything. Just Testing."
        )
    }
}

val ruleSet = ruleSet(ortResult, licenseInfoResolver, resolutionProvider) {
  noMITRule()
}

ruleViolations += ruleSet.violations
