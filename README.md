## News App
- The app fetches a list of news headlines and presents them in a feed
- Clicking on a headline will open up a detail page
- Basic error/retry handling is in place
- The headlines are hosted on GitHub as static JSON responses

### App architecture
- The project follows a clean architecture approach
- Features are separated into packages
    - `domain` (business logic)
    - `data` (I/O operations)
    - `presentation` (model, state, actions)
    - `ui` (UI components) 
    - `di` (Koin module declarations)
  
### Presentational architecture
- The project's follows an MVI pattern for rendering UIs
    - A model receives actions and emits state update events
    - The model can perform asynchronous operations using coroutines 
    - The root composable (see FeedUi.kt)[https://github.com/greghynds/clean-feed/blob/main/app/src/main/java/com/allsouls/newsapp/feed/ui/FeedUi.kt] listens to the 
      model and recomposes the components on each update
    - UI interactions are handled via a delegate interface which the Activity implements 
  
### Frameworks
- Kotlin coroutines for async operations
- Retrofit for network I/O
- Koin for dependency injection
- Jetpack Compose for UI components
- LiveData for reactive programming  
- Mockito for mocking test dependencies
- AssertJ for unit test assertions
- approvals-java for text-based approval tests

###  Todo
- The build script could be improved. First steps might be to extract version numbers into properties, create files for different sections (e.g. dependencies). 
- The UI is very basic and the components themselves could be separated into smaller slices
- No integration or e2e tests - it would make sense to use the dedicated testing API for Jetpack Compose
- A caching or offline feature might be a nice bonus. In a simple case, the content could be written to disk as raw JSON files
