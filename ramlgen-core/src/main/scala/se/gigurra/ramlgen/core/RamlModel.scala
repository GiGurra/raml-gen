package se.gigurra.ramlgen.core

import java.io.{File, Reader}

import org.raml.v2.api.model.v10.api.{Api => V10Model}
import org.raml.v2.api.model.v10.datamodel.TypeDeclaration
import org.raml.v2.api.model.v10.security.{SecurityScheme, SecuritySchemeRef}
import org.raml.v2.api.{RamlModelBuilder, RamlModelResult}

import scala.collection.JavaConversions._

/**
  * Created by johan on 2016-09-25.
  */
class RamlModel(model: V10Model) {
  val title: String = model.title.value
  val baseUri: String = model.baseUri.value
  val baseUriParameters: Seq[TypeDeclaration] = model.baseUriParameters
  val protocols: Seq[String] = model.protocols
  val mediaTypes: Seq[String] = model.mediaType.map(_.value)

  /**
    * These security schemes apply to all resources in this Model
    */
  val securedBy: Seq[SecuritySchemeRef] = model.securedBy

  /**
    * These are all the schemes defined throughout the model - Both
    * those that apply to all resources in this mode (see securedBy)
    * and those that are used for specific resources
    */
  val securitySchemas: Seq[SecurityScheme] = model.securitySchemes

  ///.. .. ..
  // RAML is tooo huuuuge >P> Maybe I could generate code for a subset.. but types through security... that's a bigger problem :S
}

object RamlModel {

  def fromLocation(location: String): RamlModel = apply(new RamlModelBuilder().buildApi(location))
  def fromFile(file: File): RamlModel = apply(new RamlModelBuilder().buildApi(file))
  def fromReader(reader: Reader, location: String): RamlModel = apply(new RamlModelBuilder().buildApi(reader, location))
  def fromContent(content: Reader, location: String): RamlModel = apply(new RamlModelBuilder().buildApi(content, location))

  def apply(parseResult: RamlModelResult): RamlModel = {
    if (parseResult.hasErrors)
      throw RamlParseError(parseResult.getValidationResults)
    new RamlModel(Option(parseResult.getApiV10).getOrElse(throw UnsupportedRamlVersion(s"<1.0")))
  }
}
