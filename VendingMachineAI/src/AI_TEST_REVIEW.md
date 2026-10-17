Link to the prompt: https://claude.ai/share/f143206c-e4bf-4977-9eb4-14982a702599 



Manual suite from Task 1:
Approx. # Tests: 13
Statement/Line Coverage: 100%
Branch Coverage: 95.45%
Notable Strength: It followed Arrange → Act → Assert and its easy to follow. 
Notable weakness: it didnt achieve 100% branch coverage

Initial AI-generated suite:
Approx. # Tests: 35
Statement/Line Coverage: 100%
Branch Coverage: 95.45%
Notable Strength: I believe that it does test every single possible thing it could. It had meaningful assertions.
Notable weakness: It doesnt follow the Arrange → Act → Assert and it didnt achieve 100% branch coverage. 

AI suite after coverage-guided prompt
Approx. # Tests: 20
Statement/Line Coverage: 100%
Branch Coverage: 95.45%
Notable Strength: It followed Arrange → Act → Assert. It had meaningful assertions.
Notable weakness: it didnt achieve 100% branch coverage
--------------------------------------------------------------------------------------------------------------------------------------------------

The overall quality of the generated test is good. I believe they are good tests but i just dont like it. It added so many things to the code. LIke Executable act and display name, why did it add that, i dont know but at least it was clean and reabable. What the AI did do particularly well is create test in a short time, plus it adjusts to your prompts. You can control many things. IF you dont like it, you can ask it to change it. One thing that the Ai did poorly was that it didnt make it a public class which cause it to not be able to be tested. 

The AI-generated test suite compared with my manually designed suite from Project 2/Task 1 produced the same in terms of coverage and might have test more different combination of things in the code. The coverage-guided prompting did improve the generated suite because it brought down the number of tests from 35 to 20 and added the Arrange -> Act -> Assert. 

The AI generate did produce too many test. I was able to recognize them because of the sheer number of test. I feel like it could of group some test together rather than have them as its own test because it justs makes it too long in my opinion. This exercise suggest about the strengths of Ai is in the time it saves you and its power to tests things you didnt consider. The limitations of using AI to generate software tests is that it could produce some redundant test.