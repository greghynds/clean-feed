## News App Playground
- Fetches a list of news headlines and presents them in a feed
- Clicking on a headline will open up a detail page
- Basic error/retry handling is in place
- The headlines are hosted on GitHub as static JSON responses

### App architecture
- Clean architecture approach for structuring the code
- Features are separated into packages
    - `domain` (business logic)
    - `data` (I/O operations)
    - `presentation` (model, state, actions)
    - `ui` (UI components) 
    - `di` (Koin module declarations)
  
### Presentational architecture
- The project follows an MVI pattern for rendering UIs
    - A [model](https://github.com/greghynds/clean-feed/blob/main/app/src/main/java/com/allsouls/newsapp/feed/presentation/FeedModel.kt) receives actions and emits state update events
    - The model can perform asynchronous operations using coroutines 
    - The [root composable](https://github.com/greghynds/clean-feed/blob/main/app/src/main/java/com/allsouls/newsapp/feed/ui/FeedUi.kt) listens to the model and recomposes the components on each update 
    - UI interactions are handled via a [delegate interface](https://github.com/greghynds/clean-feed/blob/main/app/src/main/java/com/allsouls/newsapp/feed/presentation/FeedDelegate.kt) which the Activity implements 
  
### Frameworks
- Kotlin coroutines for async operations
- Retrofit for network I/O
- Koin for dependency injection
- Jetpack Compose for UI components
- LiveData for reactive programming  
- Mockito for mocking test dependencies
- AssertJ for unit test assertions
- approvals-java for text-based approval tests
