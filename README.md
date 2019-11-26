## News App


### Overview
- 'Clean' architecture approach.
- Features are separated into packages:
    - domain (business logic)
    - data (I/O operations)
    - presentation (UI <=> domain)
    - ui (Android UI + app components) 
    - di (Koin module declarations)
- Model View Presenter for UI architecture (passive view).
- Network requests tracked using Retrofit interceptor.
  
### Frameworks
- Kotlin coroutines for async operations.
- Retrofit for network I/O.
- Koin for dependency injection.
- Mockito for mocking test dependencies.

###  Todo
- The build script could be improved. First steps might be to extract version numbers into properties, create files for different sections (e.g. dependencies). 
- Functional tests should be added. I usually aim to run each screen on a device against a set of UI tests and a set of acceptance tests.
- Testing coverage could be increased. For instance, there's no coverage for the tracking interceptor but the core logic could be encapsulated and tested separately.
- A caching or offline feature might be a nice bonus. In a simple case, the content could be stored in SharedPreferences as JSON strings.
