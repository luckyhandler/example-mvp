# example_mvp

This project demonstrates how to use MVP and what value it has to the 
developer by extracting the ui logic into a testable - non Android-related class  

The presenter is not used to preserve activity state in this example as the data is cached in a repository. This is completely fine for most use cases. The only thing which has to be considered when following this approch is that configuration changes don't reload the data from the backend but also don't preserve list position states or similar. This has to be implemented on top of the the given solution.

Apart from that it is the most simple solution I found...
