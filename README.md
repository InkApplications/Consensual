Consensual
==========

Consensual is a library intended to make it *easy* to inform your users
of how their data is being used and shared with third-parties.

Installation
------------

Base Functionality can be included with:

    repositories {
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        implementation "com.github.InkApplications.Consensual:core:0.1.0"
    }

Approximate definitions of commonly used 3rd-party services can be 
included with: 

    implementation "com.github.InkApplications.Consensual:core:0.1.0"
    
Usage
-----

### Create your Disclosure Report

A Privacy Disclosure Report is made of different services that are
collecting information about a user. You define each of these services
as a `Service`
Every Service needs a Purpose to define why the data is being 
collected. Typical examples of this include analytics, crash reports,
or payment processing.

```kotlin
val crashReports = Purpose(
    icon = R.drawable.ic_crash_black_40dp,
    title = R.string.crash_reporting
)

val crashlytics = Service(
    type = crashReports,
    name = R.string.crashlytics
)
```

Any service may collect one or more points of data about the user.
To define the information being collected, you create a 
`PersonalData` object.

```kotlin
val identity = PersonalData(
    name = R.string.identity_information
)
val usage = PersonalData(
    name = R.string.usage_information
)
```

Every service that is added to the report can collect any bits of 
information about the user. Since not all apps are using all of the
potential data collections of all services, these are tied together
in as a `CollectionPoint`

```kotlin
val crashlyticsData = CollectionPoint(
    service = crashlytics,
    data = setOf(identity, usage)
)
```

The last thing to do is to tie it together in a Report:

```kotlin
val report = DisclosureReport(
    disclosures = setOf(crashlyticsData),
    message = R.string.user_privacy_description,
    title = R.string.user_privacy_report
)
```

### Showing the Report

Once you have your report, you can bring it up in a pre-made activity
by calling the extension method: `Activity.showDisclosure(DisclosureReport)`
in any activity.

Consensual automatically tracks if the user has accepted the 
report already and will not show it again. You can check if the user
needs to accept the report by calling the extension method: 
`Context.shouldShowDisclosure(DisclosureReport)`

The Disclosure Activity is started for a result with the request code
constant `DISCLOSURE_REQUEST` and can be monitored for the results of
`DISCLOSURE_ACCEPTED` and `DISCLOSURE_CANCELED`

```kotlin
class MyActivity: Activiy() {

    // ...
    
    fun showDisclosure(report: DisclosureReport) {
        if (this.shouldShowDisclosure(report)) {
            this.showDisclosure(reoprt)
        } else {
            // Report has already been accepted
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            DISCLOSURE_CANCELED -> {
                // User cancelled out of the report
            }
            DISCLOSURE_ACCEPTED -> {
                // User accepted the report terms
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
```
    
Disclaimer
----------

This is not a privacy policy and is not intended as any legal 
document or advice.

This software is not guaranteed to bring your app into compliance with
any local privacy, or any other laws or terms of service you are 
required to follow. It makes no guarantee that the definitions of 
3rd-party or any other services are or will remain correct in the 
future. You should always check that your app meets your requirements
and that the information displayed is accurate.
