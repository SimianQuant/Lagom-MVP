Wiring
===

The application will have a frontend play project and one microservice. Lagom encourages decoupling the API of a service from its implementation, so the project will have three main projects (and a root project). This section focuses on how the three are to be wired together. Although it is possible to abstract away the wiring using a template, understanding it is necessary when you need to go beyond the basics. 