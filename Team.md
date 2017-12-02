# NOTE:
### DOES NOT BUILD ON TRAVIS
We are unsure as to why - but it builds locally and via Gradle.
For some reason, the JUnit tests that test the server do not work
(somewhat) on travis - (1 fails, the rest pass). We believe this
has to do with how travis handles the threading, but we cannot be sure.
Thus - coveralls has not built in a good while.
If you are to analyze our code coverage - please do via JaCoCo - if at all possible.
Feel free to email for any questions.
The test file ServerTest is the issue - without it travis should be fine.
# Work Division
## Miles
* Preliminary Database Construction
* JSON parsing
* K-Means Clustering
* ANTLR setup

## Braeden:
* Database Completion
* Predictor Function
* Server Construction
* Server Concurrency
* Basic Requests
* ANTLR Completion
* Structured Queries Implementation
* So many Specifications
