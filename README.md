
Shamir's Secret Solver 🔑
A Java implementation to uncover a polynomial's secret using Lagrange Interpolation. This project solves a version of Shamir's Secret Sharing, where a secret is encoded as the constant term of a polynomial.

📖 Project Overview
This program solves for the constant term c of an unknown polynomial given a set of points (roots) that lie on its curve. It's a classic cryptographic challenge that demonstrates how a secret can be split into multiple "shares," requiring a minimum number of them to be brought together to reconstruct the original secret.

The project is built to handle two specific challenges:

Large Number Arithmetic: The polynomial's coefficients can be up to 256-bit integers, requiring the use of java.math.BigInteger for all calculations.

Multi-Base Value Decoding: The input points are provided in a Json format where the y values are encoded in various number bases (e.g., binary, hexadecimal, base 10).

✨ Features
Polynomial Secret Recovery: Implements Lagrange Interpolation to find the secret value c (the polynomial's value at x=0).

Robust Fractional Arithmetic: Correctly handles the rational number calculations inherent in Lagrange Interpolation by using a common denominator approach to avoid precision loss with large integers.

Dynamic JSON Parsing: Uses the Jackson library to parse input test cases with flexible structures.

Clean, Executable Build: Managed by Maven, providing a simple and repeatable build and execution cycle.

🚀 How to Run
To run this project, you will need Java (JDK 11+) and Apache Maven.

1. Clone the Repository
   Bash

git clone <your-repository-url>
cd <your-repository-name>
2. Build and Execute
   The project is configured to solve both test cases and print the secrets. Run the following command from the project's root directory:

Bash

mvn clean install exec:java
This command will:

clean: Delete any old compiled files.

install: Compile the source code and install dependencies.

exec:java: Run the main application.

🖥️ Expected Output
After running the command, you should see the following output in your console:

Bash

Solving for secrets with corrected logic...
Processing testcase1.json with k=3
Secret for Test Case 1: 3

-----

Processing testcase2.json with k=7
DEBUG: Final Numerator   = -9001333331893386133311221760
DEBUG: Global Denominator = -732329245881480161351680
Secret for Test Case 2: 123456789
🛠️ Tech Stack
Language: Java

Build Tool: Apache Maven

Dependencies: Jackson Databind (for JSON parsing)

📄 License
This project is distributed under the MIT License. See the LICENSE file for more information.
