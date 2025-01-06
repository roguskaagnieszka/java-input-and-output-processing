# Java I/O Processing

This project demonstrates input and output processing in Java, showcasing file manipulation and web data fetching. It consists of two main functionalities:

## Features

### 1. Text File Transformer
The `TextFileTransformer` class reads a local file, replaces spaces with hyphens, and writes the modified content to a new file. It includes:
- Reading a file using `FileInputStream`.
- Replacing spaces (`' '`) with hyphens (`'-'`).
- Writing the result to a new file using `FileOutputStream`.

### 2. Account Bank Finder
The `AccountBankFinder` class fetches bank data from the National Bank of Poland (NBP) website and identifies banks based on account prefixes. It includes:
- Fetching data from an online source.
- Parsing the file to extract unique bank names.
- Displaying the bank(s) associated with the provided account prefix.

## Usage

1. **Text File Transformer**:
    - Ensure `src/files/sourceFile.txt` exists.
    - Run the `TextFileTransformer` class to process the file and save the result in `src/files/destinationFile.txt`.

2. **Account Bank Finder**:
    - Run the `AccountBankFinder` class and provide the first three digits of an account number to retrieve matching bank names.
