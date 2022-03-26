# Aptoide clone

A very rudimentary version of the Aptoide store.

### What it does?
It consumes the Aptoide API and displays some apps available on the store, in a similar format to the original store.
If the users click one item in the screen they can view more details in another screen namely app name, store name, rating, downloads, download size, version, and package path. 

### Packages
- **Retrofit** - used to handle network calls 
- **RxJava** - for the asynchronous and event based behaviour of the app
- **RxJavaRetrofitAdapter** - integrate retrofit with RxJava
- **Material** - To add material design to the app
- **Navigation component** - initialy to handle navigation in the app and for the safe arguments API, however as adding fragment is not yet supported FragmentManager was used.  
- **JUnit4 and Espresso** - Tests

### Architecture - ViewModel
The application follows ViewModel as it offers better separation of responsibilities than other UI architectural patterns Architectures such as MVP and MVC. In addition, it is the recommended architecture by Google, which has included several components in the Android API that implement this architecture. Finally, the ViewModel component is resistant to configuration changes which is a plus although in this case the App is locked on portrait mode.

## Additional Features
- Retries connection 2 extra times
- Notifies of network problems
- Enables user triggered retrial of connection 
- Adapts to dark mode and light mode
- Custom animations between fragments
