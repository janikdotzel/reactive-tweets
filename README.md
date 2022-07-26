# Reactive-Tweets

This repository is designed to be a demo project for showcasing Akka Streams in Scala. 
The name "Reactive Tweets" comes from the equal named Quickstart Guide.
It is extended to load with more Sources, Flows and Sinks to get more practical hands-on experience.

To make the exploration more interesting I implemented a Twitter Client to load real Twitter data.

## Getting Started
In order to experiment with Akka Streams on your own you can simply clone this project.

### Navigation:
These files are the ones you should work with
- [Stream Components:](janikdotzel/reactive-tweets/src/main/scala/janikdotzel/reactivetweets/StreamComponents.scala) 
Building blocks for your Stream. Modify the existing ones or create your own.
- [Main:](src/main/scala/janikdotzel/reactivetweets/Main.scala) 
Creation of runnable graphs

bearer token needed for pulling twitter data 

### Tags
- 1.1.0
- 1.2.0
- 1.3.0

## Links:
- Akka Documentation of Reactive Tweets: 
https://doc.akka.io/docs/akka/current/stream/stream-quickstart.html#reactive-tweets
- Official Twitter API V2:
https://developer.twitter.com/en/docs/twitter-api
- Akka Stream Operators:
https://doc.akka.io/docs/akka/current/stream/operators/index.html