# Encryption/Decryption CLI Tool

This command-line tool provides a simple interface for encrypting and decrypting text data using either a shift cipher or Unicode-based encryption. It supports reading input from a file, specifying encryption keys, and outputting the result to the console or a designated file.

## Usage

To use the tool, run the following command in the terminal:

```bash
javac Main.java [options]
```
## Options
* -mode: Specifies the operation mode. Use enc for encryption (default) or dec for decryption.
* -key: Specifies the encryption or decryption key. Default is 0.
* -data: Specifies the input data to be encrypted or decrypted.
* -in: Specifies the input file containing data to be encrypted or decrypted.
* -out: Specifies the output file to save the result. If not specified, the result will be printed to the console.
* -alg: Specifies the algorithm for encryption or decryption. Use shift (default) for a shift cipher or unicode for Unicode-based encryption.

## Examples
Encrypting a message:
```bash
javac Main.java -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode
```
Decrypting a message:
```bash
javac Main.java -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
```

