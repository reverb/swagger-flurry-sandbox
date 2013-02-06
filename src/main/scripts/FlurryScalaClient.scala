import com.wordnik.swagger.codegen.BasicScalaGenerator

object FlurryScalaClient extends BasicScalaGenerator {
  def main(args: Array[String]) = generateClient(args)

  // package for api invoker, error files
  override def invokerPackage = Some("com.reverb.client")
    
  // where to write generated code
  override def destinationDir = "client/flurry/scala/src/main/scala"

  // package for models
  override def modelPackage = Some("com.reverb.flurry.model")

  // package for api classes
  override def apiPackage = Some("com.reverb.flurry.api")

  override def templateDir = "src/main/resources/scala"

  override def toVarName(name: String): String = {
  	super.toVarName(name.replaceAll("@", "_"))
  }

  // supporting classes
  override def supportingFiles = List(
    ("apiInvoker.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replaceAll("\\.", java.io.File.separator) + java.io.File.separator, "ApiInvoker.scala"),
    ("pom.mustache", "client/flurry/scala", "pom.xml")
  )
}