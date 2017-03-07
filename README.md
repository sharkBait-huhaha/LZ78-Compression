# COMP317-17A_AssignmentOne_LZ78-Compression
Implement LZ78 compression and decompression. 

LZ78 Compression

Due: Monday, April 3rd, 2017 — 09:00am
Description: Implement LZ78 compression and decompression. This assignment is to be done in pairs (i.e. with a partner), and implemented in Java such that your code compiles and runs on the Linux machines in Lab 6.

Specification: Implement the LZ78 compression and decompression routines according to the following specifications:

A complete solution will have six parts: an encoder, a binarizer, a bit-packer, a bit-unpacker, a debinarizer, and a decoder—all separate programs that read standard input and produce standard output.
Your encoder takes its input as a stream of bytes from standard input, and uses the LZ78 algorithm to output pairs, where each pair consists of a phrase number and a mismatched byte, one pair per line of output.
Given the output of your encoder as input, the output of your decoder should exactly match the input to the encoder (i.e. this is the complement operation to your encoder).
The encoder must implement the dictionary as a multi-way trie.
Your binarizer takes as input the output pairs from your encoder and converts them into the corresponding bit sequence for the minimum-length phrase number and mismatched byte, expressed as a character string of 0's and 1's (no linebreaks).
Given the output of your binarizer as input, the output of your debinarizer should exactly match the input to the binarizer (i.e. this is the complement operation to your binarizer).
Your bit-packer takes as input the output from your binarizer and converts the string of 0's and 1's into the actual corresponding sequence of bits, packed into a stream of bytes and sent to standard out.
Given the output of your bit-packer as input, the output of your bit-unpacker should exactly match the input to the bit-packer (i.e. this is the complement operation to your bit-packer).
The encoder must accept as a commandline argument one integer specifying the maximum number of bits that can be used to encode a phrase number (i.e. a limit on the size of the dictionary). If the dictionary becomes full, the encoder should emit the phrase number for the RESET symbol, then throw away all acquired phrases and continue encoding as if starting from scratch.
If the decoder reads the phrase number for the RESET symbol, it must throw away all acquired phrases and continue decoding as if starting from scratch.
The binarizer and bit-packer must also deal with the RESET code appropriately, as must the complement operations.
Write a shell script for compression, called lz78c, that takes as an argument the name of a file to compress. If a second argument is provided it must be a positive integer specifying the maximum number of bits your encoder can use for phrase numbers. If this argument is missing, it should default to 16 in your script. This script calls the encoder, binarizer and packer, and saves the final output in a file with the same name as the input file but with ".lz78" appended.
Write a shell script for decompression called lz78d, that takes as an argument the name of file compressed with your compression script and having the ".lz78" suffix, then pipes the contents of that file through your unpacker, debinarizer and decoder, storing the result in a file whose name is the same as the input file but with the suffix removed.
Approach: I suggest you get your encoder to work first then build your decoder and test them as a pair. Once this works, turn your attention to building the binarizer and debinarizer, and finally the packer and unpacker. A passing mark is possible if just the encoder and decoder work, but a mark above B is only possible if your programs comply with all specifications.

Hand in: Submission is done electronically via Moodle. Place into an otherwise empty directory only your source code, scripts and an optional plain-text README file. Name the directory ID1_ID2, where ID1 and ID2 are the student ID numbers of the partnership. Create a single, compressed tarball (see the unix manual on "tar") and have one member of the partnership upload your solution via Moodle. The other partner should check that the upload was successful. Marks are awarded based upon conformance to the specification and quality of the code, including format and documentation (i.e. it should look nice and be understandable). Names and student ID numbers for both partners should be in the header documentation of all files.

Challenge: It is not required for this assignment, but perhaps of interest as an added challenge, you could add a STOP symbol to your initial dictionary. Once the dictionary is full, the encoder emits the STOP symbol and continues using the dictionary in its current state. Only if the compression ratio starts to deteriorate does the encoder then emit the RESET symbol to start again. All other components that depend on knowing the dictinary size must be adjusted to react appropriately to this new symbol.

Tony C. Smith, 06/03/2017
http://www.cs.waikato.ac.nz/~tcs/COMP317/assign1-2017.html
