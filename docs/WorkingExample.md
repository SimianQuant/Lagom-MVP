Working Example
===

To motivate the tutorial, we will build an application that will allow users to record their mood, view a collated record of their mood and query users by mood, sort of like a stripped down version of [Daylio](https://daylio.webflow.io/). While simple by design, the tutorial will cover:

1. How to wire a Play based fontend with Lagom microservices based backend
1. How to integrate persistence
1. How to deploy the application on a hosted server

In the process, it will deviate from two recommendations/best practices of the framework.

1. **We will use an RDBMS (specifically PostgreSQL) for persistence, not Cassandra**.
    1. NoSQL databases are more efficient if the queries are known ahead of time, but this condition is almost certainly going to be false as you are building the first few versions of the applications
    1. Cassandra is better suited from the perspective of fault tolerence and replication, but this is unlikely to be a concern in the beginning
    1. Setting up and managing Cassandra is a pain, at least when compared to PostgreSQL
    1. Migrating a PostgreSQL database is simpler than migrating a Cassandra database
1. **We will deploy the application statically (i.e. on a single machine), rather than using container orchestration (e.g. using Kubernetes)**
    1. For a "few" microservices (say upto 8), writing a startup script is simpler than writing the kubernetes configuration (at least you need to learn fewer concepts)
    1. The logs are available on the filesystem and you don't need to spend the additional effort to to extract them from the pods. This makes debugging simpler.
	1. Setting up HTTPS (using nginx) is simpler than on kubernetes. 
	1. Testing and debugging a production build on a local machine is much simpler with a static deployment configuration than with minikube. 

That being said, the best practices exist for a reason, and incorporating them will become important as the application scales. But if your application has sufficient traction that scale is a concern, then it should be simple to requisition/raise capital and get other people to solve the DevOps issues. Moreover, with a microservice architecture, it is trivial to gradually migrate parts of your application to a more robust production configuration. 
