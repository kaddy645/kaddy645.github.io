# Android Reverse Engineering - Final Report

University Of Dayton
Department of Computer Science
CPS 592-02, Software/Language Based Security Fall 2020
Advisor - Dr.Phu Phung

### Team 7 - Members

Kartik Desai , desaik5@udayton.edu
Dr.Phu Phung , pphung1@udayton.edu

### Goal

- Understand what an app does from a high-level point of view
- Understand the tech details on how an app does something
- Find security vulnerabilities - exploit them to gain some advantage
- Steal private information, read private file, steal its permissions, etc.

### Project Objectives - To Study

- What is Reverse Engineering in Android?
- How Reverse Engineering can be used to decompile Android app code.
- What are some threats of a decompiled Android code?
- How to avoid Android app decompilation to prevent reverse-engineering
- How Modern Javascript Frameworks are being used to develop Hybrid and Native android applications.
- How Permission asked to user are getting implemented.
- How we can use AspectJ to study what security policies and permissions are getting used.

### Case Study - Mobile Framework

Introduction :- Developers who are develpoing android application usign tech like React Native or java , can request access to user’s data as long as they provide their users a privacy policy. they can use people’s personal information for malicious purposes. e.g Uploading this information like contact list on cloud.

Objectives:- The objectives of this project is to study the security architecture of android applications and explore whether they have any potential security
vulnerabilities and/or privacy issues.

### Code Snippets & - Aarogya Setu - Java (India)

[source](https://github.com/nic-delhi/AarogyaSetu_Android)
![app permissions](https://raw.githubusercontent.com/kaddy645/kaddy645.github.io/master/mat4.PNG)

```
 private var locationCallback: LocationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        locationResult?.let {
                            if (it.lastLocation != null) {
                                val usersLocationData =
                                    BluetoothData(
                                        Constants.EMPTY,
                                        0,
                                        Constants.EMPTY,
                                        Constants.EMPTY
                                    )
                 usersLocationData.latitude = it.lastLocation.latitude
                 usersLocationData.longitude = it.lastLocation.longitude
                 CoronaApplication.getInstance().setBestLocation(it.lastLocation)
                 Logger.d( "Retreive location service", usersLocationData.latitude.toString() + " - " + usersLocationData.longitude.toString())
                   DBManager.insertNearbyDetectedDeviceInfo(listOf(usersLocationData))
                            }
                        }

                    }
                }
```

![bluezone permissions](https://raw.githubusercontent.com/kaddy645/kaddy645.github.io/master/B3.PNG)

### Code Snippets : - Bluezone - React Native(Vietnam)

[source](https://github.com/BluezoneGlobal/bluezone-app)

```
onPress() {
                    const {numberPhone} = this.state;
                    const {intl} = this.props;
                    const {formatMessage} = intl;
                    const vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
                    if (vnf_regex.test(numberPhone) === false) {
                      Alert.alert(formatMessage(message.phoneEnterNotValid));
                    } else {
                      this.setState({showLoading: true, showErrorModal: false}, () => {
                        CreateAndSendOTPCode(
                          numberPhone,
                          this.createAndSendOTPCodeSuccess,
                          this.createAndSendOTPCodeFail,
                        );
                      });
                    }
                  }

                  createAndSendOTPCodeSuccess(response) {
                    const {numberPhone} = this.state;
                    const {setLoading} = this.props;
                    const router = setLoading ? 'VerifyOTPAuth' : 'VerifyOTP';

                    this.setState({showLoading: false}, () => {
                      setTimeout(() => {
                        this.props.navigation.replace(router, {
                          phoneNumber: numberPhone,
                        });
                      }, 200);
                    });
                  }

```

### Project Management

- [Trello](https://trello.com/sslbsf20group7)
- [Bitbucket](https://bitbucket.org/ss-lbs-f20-group7/arengineering/src/master/)

### Project Accomplishment - Get Started!

- [Webpage](https://kaddy645.github.io/) - A Github page on How to get started with Android Reverse Engineering, Tools used, Apps used for testing Purpose,Presentation

### Conclusion

- By Reverse Engineering Android Apps, we have understood that it is important for developers to know the security aspect of mobile applications. we have learned to take an APK, unpack it, reverse it, and understand what it's doing, find, & exploit bugs.
