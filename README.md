### Hexlet tests and linter status:
[![Actions Status](https://github.com/honest-niceman/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/honest-niceman/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/1f2f3ae2f46f16a91cb8/maintainability)](https://codeclimate.com/github/honest-niceman/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/1f2f3ae2f46f16a91cb8/test_coverage)](https://codeclimate.com/github/honest-niceman/java-project-71/test_coverage)

# Diff Finder

The repository contains a source code for a CLI tool that helps to find the difference between two data structures.

## Help

![help.png](pics/help.png)

## Basic flow

Just run the app and pass two files (both .json or .yaml) as parameters. The program will print out the difference 
between them: 

![compare-two-jsons.png](pics/compare-two-jsons.png)

## Formats

The program supports three formats:

- stylish (the default one)
- plain
- json

### Stylish
![compare-two-jsons.png](pics/compare-two-jsons.png)

### Plain
![plain-format.png](pics/plain-format.png)

### Json

![json-format.png](pics/json-format.png)
