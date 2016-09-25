package se.gigurra.ramlgen.core

/**
  * Created by johan on 2016-09-25.
  */
case class UnsupportedRamlVersion(version: String)
  extends IllegalArgumentException(s"Support for RAML version $version not implemented")
