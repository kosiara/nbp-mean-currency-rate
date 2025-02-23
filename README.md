This study project gets currency rates from NBP API under: https://api.nbp.pl/

Module separation focuses on performant builds - separating common Android components into a common Android module. 

We do have: 
common - with extensions, design-system, components and navigation
data - with models, data-sources and networking
domain - with repositories and use-cases
features - with specific app features/screens
app - main Android module

Application is built using Jetpack Compose, coroutines

Extra: 
* unit tests in common (kotlin) module
* screen-shot tests from Jetpack in common (Android) module
