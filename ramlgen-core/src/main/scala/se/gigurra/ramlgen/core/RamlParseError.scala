package se.gigurra.ramlgen.core

import org.raml.v2.api.model.common.ValidationResult

/**
  * Created by johan on 2016-09-25.
  */
case class RamlParseError(validationResults: Seq[ValidationResult])
  extends IllegalArgumentException(s"Failed to parse raml, errors: $validationResults")
