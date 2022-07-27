# Reactive-Tweets

## Table of Contents

* [Introduction](#introduction)
* [Getting Started](#getting-started)
    * [Twitter API access](#twitter-api-access)
* [Tasks](#tasks)
    * [Print Tweets with the 'akka' hashtag](#print-tweets-with-the-akka-hashtag)
    * [Use a Json File as a Source](#use-a-json-file-as-a-source)
    * [Print tweets obtained from the Twitter API](#print-tweets-obtained-from-the-twitter-api)
* [Links:](#links)

## Introduction
This a demo project for showcasing Akka Streams in Scala. The name "Reactive Tweets" comes from the equal named Quickstart Guide.
It is extended to load with more Sources, Flows and Sinks to get more practical hands-on experience.

To make the exploration more interesting I implemented a Twitter Client to load real-time Twitter data.

## Getting Started
In order to experiment with Akka Streams on your own you can simply clone this project.
```bash
git clone https://github.com/janikdotzel/reactive-tweets.git
```
These files are the ones you should look up and modify:
- [Stream Components:](src/main/scala/janikdotzel/reactivetweets/StreamComponents.scala) 
Building blocks for your Stream. Modify the existing ones or create your own.
- [Main:](src/main/scala/janikdotzel/reactivetweets/Main.scala) 
Creation of runnable graphs & Running the app

### Twitter API access
In order to use the request data from the official Twitter API you need to get a Bearer Token.
Please visit https://developer.twitter.com for more information.

After you got your Bearer Token you need to add it to your environment variables.
```bash 
export BEARER_TOKEN=<PERSONAL BEARER TOKEN>
```

## Tasks
There are currently 3 tasks which you can solve on your own.
To get started with each task, checkout the respective git tag.

### Print Tweets with the 'akka' hashtag
```
git checkout print-akka-tweets
```
### Use a Json File as a Source
```
git checkout json-source
```
### Print tweets obtained from the Twitter API
```
git checkout print-real-scala-tweets
```



## Links:
- Akka Documentation of Reactive Tweets: 
https://doc.akka.io/docs/akka/current/stream/stream-quickstart.html#reactive-tweets
- Official Twitter API V2:
https://developer.twitter.com/en/docs/twitter-api
- Akka Stream Operators:
https://doc.akka.io/docs/akka/current/stream/operators/index.html
- Twitter Authentication explained: 
https://developer.twitter.com/en/docs/authentication/oauth-2-0/bearer-tokens