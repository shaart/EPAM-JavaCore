## Task 4. 
Create simple java application (for example text file parsing or something else) with at least two memory leaks and find them using Yourkit Profiler tool.

| Parameters     | Value |
| -------------- | --------------------------- |
| JVM parameters | <code>-Xms8m -Xmx64m</code> |
| Profiler startup options | <code>onexit=memory,usedmem=50,alloceach=4</code> |

### YourKit Java Profiler Screenshots from program shutdown snapshot
<details>
  <summary>
    Objects generation: a lot of <code>Long</code> and <code>Integer</code> objects
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/1.png">
</details>
<details>
  <summary>
    Objects reachability: a lot of unreachable <code>Long</code> and <code>Integer</code> objects
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/2.png">
</details>
<details>
  <summary>
    Classes and Allocation by Call Tree: <code>Integer</code> objects from <code>LeakedAdded.addInt(int)</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/3.png">
</details>
<details>
  <summary>
    Classes and Allocation by Call Tree: <code>Long</code> objects without allocation information
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/4.png">
</details>
<details>
  <summary>
    Garbage Objects: big amount of <code>Long</code> was created at <code>LeakedAdded</code> by <code>Long.valueOf()</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/5.png">
</details>
<details>
  <summary>
    Garbage Objects: big amount of <code>Integer</code> was created at <code>LeakedAdded</code> by <code>Integer.valueOf()</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/tasks/task4/screenshots/6.png">
</details>
