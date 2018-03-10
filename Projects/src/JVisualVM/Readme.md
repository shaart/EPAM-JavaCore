## Task 1. 
### Apply changes to the following app code to make GC curve have peaks that are more frequent.
<details>
  <summary>
    Without changes (<code>initialCode()</code> method)
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/WithoutChanges.png">
</details>
<details>
  <summary>
    With adding <code>System.gc()</code> (<code>callGCEveryTime()</code> method)
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/GCCallingEveryTime.png">
</details>
<details>
  <summary>
    With removing <code>List&lt;Object&gt;</code> and modifying GC call (<code>moreFrequentPeaks()</code> method)
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaks.png">
</details>

## Task 2. 
### Tune GC settings via JVM flags (change GC, maybe) to make GC curve have peaks that are more frequent.
<details>
  <summary>
    <code>initialCode()</code> method with JVM options: <code>-XX:+UseG1GC</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/WithoutChangesG1GC.png">
</details>
<details>
  <summary>
    <code>moreFrequentPeaks()</code> method with JVM options: <code>-XX:+UseG1GC</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaksG1GC.png">
</details>
<details>
  <summary>
    <code>moreFrequentPeaks()</code> method with JVM options: <code>-XX:+UseParallelGC</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaksParallelGC.png">
</details>
<details>
  <summary>
    <code>moreFrequentPeaks()</code> method with JVM options: <code>-XX:+UseSerialGCC</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaksSerialGC.png">
</details>

## Task 3. 
### Tune Heap regions via JVM flags to make GC curve have peaks that are more frequent.
<details>
  <summary>
    <code>moreFrequentPeaks()</code> method with JVM options: <code>-Xmx128m -Xms128m</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaksXmx128mXms128m.png">
</details>
<details>
  <summary>
    <code>moreFrequentPeaks()</code> method with JVM options: <code>-Xmx1g -Xms1g</code>
  </summary>
  <img src="https://github.com/shaart/EPAM-JavaCore/blob/master/Projects/src/JVisualVM/Screenshots/MoreFrequentPeaksXmx1gXms1g.png">
</details>
