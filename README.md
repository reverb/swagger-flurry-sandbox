Swagger Flurry Sandbox
==========

This is a project to enable Swagger on the Flurry analytics API.  It does so
by manually creating resource listings here:

https://github.com/reverb/swagger-flurry-sandbox/tree/master/src/main/webapp/flurry

And serving them up in an embedded jetty web server.  Then the [swagger-ui](https://github.com/wordnik/swagger-ui) can access the Swagger JSON files from your localhost, and make calls directly to the Flurry API, as documented [here](http://support.flurry.com/index.php?title=API_Usage).

Of course, you'll need to get your own `apiKey` and `apiAccessCode`s from Flurry.  But once done, you can browse _your_ data with the simplicity of Swagger.

Usage
-----

Clone the repo and run the jetty server (note!  you can run this behind just about any web server or container.  This one is just *easy*):

```
mvn jetty:run
```

Now open your browser and see the API in swagger:

```
http://localhost:8000
```

![Screenshot](https://raw.github.com/reverb/swagger-flurry-sandbox/master/screen-shot.png)


Voila!  Browse your data in an elegant fashion.  But wait, there's more...

If you want to create a client to hit the Flurry API and pull your info programmatically, you can do so by running the [swagger-codegen](https://github.com/wordnik/swagger-codegen) and building a client lib.  There's a simple script to do so in scala:

```
# NOTE!  You need scala 2.9.x in your $PATH to run this script
# go get it from http://www.scala-lang.org/sites/default/files/linuxsoft_archives/downloads/distrib/files/scala-2.9.1-1.zip

./bin/scala-client.sh
```

Which will create a client in `client/flurry/scala`.  You can build the client library with `mvn package`

Of cource you can do this for other languages.  One oddity with the flurry API is that properties in the JSON response are prefixed with `@`, which is very objective-c-ish.  Well, most languages don't like the presence of `@` in variable names, so you will have to override the codegen script of your choice as needed.  For `scala`, the work is done for you:

https://github.com/reverb/swagger-flurry-sandbox/blob/master/src/main/scripts/FlurryScalaClient.scala

```scala
override def toVarName(name: String): String = {
  super.toVarName(name.replaceAll("@", "_"))
}
```

Pro-tip
-------

Hate typing your `apiKey` and `apiAccessCode` over and over?  Edit your [api listings](https://github.com/reverb/swagger-flurry-sandbox/tree/master/src/main/webapp/flurry) and set the `defaultValue` for each field:

```json
{
  "name": "apiAccessCode",
  "description": "API Access Code",
  "paramType": "query",
  "required": true,
  "allowMultiple": false,
  "dataType": "string",
  "defaultValue": "YOUR_API_ACCESS_CODE"
},
{
  "name": "apiKey",
  "description": "API Key",
  "paramType": "query",
  "required": true,
  "allowMultiple": false,
  "dataType": "string",
  "defaultValue": "YOUR_API_KEY"
},
```

Just don't check them into your git repo!


