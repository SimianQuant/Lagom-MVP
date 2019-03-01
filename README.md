Lagom: Zero to MVP
===



[Lagom](https://www.lagomframework.com/) is a JVM framework to build applications composed of microservices. While its architecture allows teams to smoothly transition from a Miminum Viable Product (MVP) to a large application, the standard documentation assumes that its readers already working with a large application, and focuses on addressing the issues that come with scale rather than on the steps needed to deploy the simplest version of an application. This tutorial aims to bridge that gap and show how, from first principles, a Lagom based MVP can be built and deployed. 

As a motivating example, the built application will allow users to record their mood, view a collated record of their mood, and query users by mood (sort of like a stripped down version of [Daylio](https://daylio.webflow.io/)). While simple by design, it will illustrate:

1. How to wire a [Play framework](https://www.playframework.com/) based fontend with Lagom microservices based backend
1. How to integrate persistence
1. How to deploy the application on a hosted server

The full tutorial can be read [here](https://simianquant.gitbook.io/lagom-mvp/)