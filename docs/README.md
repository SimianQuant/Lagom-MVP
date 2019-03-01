Lagom: Zero to MVP
===

An application built using microservices has clear advantages over a monolithic one when it needs to scale, however the conventional wisdom is that a microservice architecture is prohibitively complex when building the first iteration of a product. The conventional wisdom is reinforced by the dense and soporific documentation of most microservice frameworks (Lagom being a prime example). 

The thesis of this tutorial is that with Lagom, building an MVP using decoupled microservices required roughly the same amount of effort as building one as a monolithic application. This requires breaking with the "best practices" of the framework, however the flexibility of the architecture allows these to be reincorporated as and when required. Therefore, if you start out using microservices, you won't need to waste time re-engineering your whole application once the product gains traction and the infrastructure needs to scale. 

The tutorial does not assume prior knowledge of Lagom, however it assumes that you are reasonably fluent in Scala, Play and sbt, or can interpolate from the context. If you follow along till the end, you should have an application with a Play frontend connected to a Lagom backend deployed on a cloud server (with HTTPS). 

## About the Author

The author is the founder of SimianQuant, a company that works on automating software implementation. The company uses Lagom (with a Play frontend) to distribute one of its products. You can learn more about the company [here](https://simianquant.com/)