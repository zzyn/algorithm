<%@ Page Language="C#" EnableViewState="false" AutoEventWireup="false" %>
<%
  // Copyright (c) by Matthias Hertel, http://www.mathertel.de
  // This work is licensed under a BSD style license. See http://www.mathertel.de/License.aspx
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head runat="server">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="KEYWORDS" content="Difference Algorithm diff compare text textlines ASP.NET DotNet C#" />
  <meta name="DESCRIPTION" content="The proven, best and most famous algorythm to identify the differences between 2 files or data." />
  <meta name="Author" content="Matthias Hertel" />
  <meta name="robots" content="index,follow" />
  <link href="http://www.mathertel.de/License.aspx" rel="copyright" />
  <link href="../mathertel.css" rel="stylesheet" type="text/css" />
  <title>An O(ND) Difference Algorithm for C#</title>
</head>

<body>
  <mh:title runat="server" />
  <p>This article is about comparing text files and the proven, best and most famous
    algorythm to identify the differences between them. The source code that you can
    find in the download implements a small class with a simple to use API that just
    does this job. You should have it in the bag of your algorythms.</p>
  <p>Beside the class that implements the algorythm there is also a sample web application
    that compares 2 files and generates html output with a combined and colored document.</p>
  <p>The algorythm was first published 20 Years ago under <strong>"An O(ND) Difference
    Algorithm and its Variations" by Eugene Myers Algorithmica Vol. 1 No. 2, 1986, p
    251</strong> . You can find a copy if it at <a href="http://xmailserver.org/diff2.pdf">
      http://xmailserver.org/diff2.pdf</a>. &nbsp;In this article you can find a abstract
    recursive definition of the algorythm using some pseudo-code that needs to be transferred
    to a existing programming language.</p>
  <p>There are many C, Java, Lisp implementations public available of this algorythm
    out there on the internet. Before I wrote the C# version I discovered that almost
    all these implementations seem to come from the same source (GNU diffutils) that
    is only available under the (unfree) GNU public license and therefore cannot be
    reused as a source code for a commercial or redistributable application without
    being trapped by the GNU license.</p>
  <p>There are very old C implementations that use other (worse) heuristic algorithms.
    Microsoft also published source code of a diff-tool (windiff) that uses some tree
    structures. Also, a direct transfer from a C source to C# is not easy because there
    is a lot of pointer arithmetic in the typical C solutions and I wanted a managed
    solution. I tried a lot sources but at least found no usable solution written for
    the .NET platform.</p>
  <p>These are the reasons why I implemented the original published algorithm from the
    scratch and made it available without the GNU license limitations under a <a rel="license"
      href="http://www.mathertel.de/License.aspx">BSD style license</a>. The history
    of this implementation is back to 2002 when I published a Visual Studio add-in that
    also can compare files, see <a href="http://www.codeproject.com/csharp/WebReports8.asp">
      http://www.codeproject.com/csharp/WebReports8.asp</a>. I found no more bugs in
    the last 3 years so I think that the code is stable.</p>
  <p>I did not need a high performance diff tool. I will do some performance tweaking
    when needed, so please let me know. I also dropped some hints in the source code
    on that topic.</p>
  <h2>How it works (briefely)</h2>
  <p>You can find a online working version at <a href="http://www.mathertel.de/">http://www.mathertel.de/</a>.</p>
  <ul>
    <li>Comparing the characters of 2 huge text files is not easy to implement and tends
      to be slow. Comparing numbers is much easier so the first step is to compute unique
      numbers for all textlines. If textlines are identical then identical numbers are
      computed.</li>
    <li>There are some options before comuting these numbers that normally are usefull
      for some kind of text: stripping off space characters and comparing case insensitive.</li>
    <li>The core algorithm itself will compare 2 arrays of numbers and the preparation
      is done in the private DiffCodes method and by using a Hashtable.
      <li>The methods DiffText and DiffInts.</li>
      <li>The core of the algorythm is built using 2 methods:<br>
        <strong>LCS</strong>: This is the divide-and-conquer implementation of the longes
        common-subsequence algorithm.<br>
        <strong>SMS</strong>: This&nbsp;method finds the Shortest Middle Snake.</li>
      <li>To get some usable performance I did some changes to the original algorithm. The
        original algorithm was described using a recursive approach and comparing zero indexed
        sequences and passes parts of these sequences as parameters. Extracting sub-arrays
        and rejoining them is very CPU and memory intensive. To avoid copying these sequences
        as arrays around the used arrays together with the lower and upper bounds are passed
        while the sequences are not copied around all the time. This circumstance makes
        the LCS and SMS functions more complicate.</li>
      <li>I added some code to the LCS function to get a fast response on sub-arrays that
        are identical, completely deleted or inserted instead of recursively analysing them
        down to the single number case.</li>
      <li>The result is stored in 2 arrays that flag for modified (deleted or inserted)
        lines in the 2 data arrays. These bits are then analyzed to produce a array of objects
        that describe the found differences.</li>
      <li>Read the original article if you want to understand more.</li>
  </ul>
  <h2>The API</h2>
  <p>To use this functionality you only have to call one of the DiffText methods. They
    all get a pair of strings and return an array of items that describe the inserts
    and deletes between the 2 strings. There are no "changes" reported. Instead you
    can find a "insert" and "deleted" pair.</p>
  <h3>DiffText(string TextA, string TextB)</h3>
  <p>Find the difference in 2 texts, comparing by textlines without any conversion.
    A array of Items containing the differences is returned.</p>
  <h3>DiffText(string TextA, string TextB, bool trimSpace, bool ignoreSpace, bool ignoreCase)</h3>
  <p>Find the difference in 2 texts, comparing by textlines with some optional conversions.
    A array of Items containing the differences is returned.</p>
  <h3>Diff(int[] ArrayA, int[] ArrayB)</h3>
  <p>Find the difference in 2 arrays of integers. A array of Items containing the differences
    is returned.</p>
  <h2>A Sample application for the algorithm</h2>
  <p>The sample is a small web application that calculates the difference of 2 text-file
    and displays the result using HTML.</p>
  <p>To setup the sample you have to create a web-project and copy all files found in
    the zip file into the directory. The implentation of the algorythm is inside the
    "diff.cs" class.</p>
  <h2>Further possible optimizations (if you really need speed)</h2>
  <p>(first rule: don't do it; second: don't do it yet)</p>
  <p>The arrays DataA and DataB are passed as parameters, but are never changed after
    the creation so they can be members of the class to avoid the parameter overhead.</p>
  <p>In SMS is a lot of boundary arithmetic in the for-D and for-k loops that can be
    done by increment and decrement of local variables. </p>
  <p>The DownVector and UpVector arrays are always created and destroyed each time the
    SMS gets called. </p>
  <p>It is possible to reuse them when transferring them to members of the class. </p>
  <p>See TODO: hints.</p>
  <h3>Security issues with the web application:</h3>
  <ul>
    <li>Using this web-site implemenation enables clients to view ALL sourcecode of you
      website. Just be shure that you do not use it as-it-is on a public server. </li>
    <li>You should implement a check of the parameters and allow only a diff output on
      files that can be displayed (text based files).</li>
  </ul>
  <h3>Changes:</h3>
  <p>This work was first published at http://www.gotdotnet.com/Community/UserSamples/Details.aspx?SampleGuid=
    96065252-BE1D-4E2F-B7CB-3695FEB0D2C3.</p>
  <p><b>2002.09.20</b></p>
  <p>There was a "hang" in some situations.</p>
  <p>Now I undestand a little bit more of the SMS algorithm. </p>
  <p>There have been overlapping boxes; that where analyzed partial differently. One
    return-point is enough.</p>
  <p>A assertion was added in CreateDiffs when in debug-mode, that counts the number
    of equal (no modified) lines in both arrays. They must be identical.</p>
  <p><b>2003.02.07</b></p>
  <p>Out of bounds error in the Up/Down vector arrays in some situations.</p>
  <p>The two vectors are now accessed using different offsets that are adjusted using
    the start k-Line.</p>
  <p>A test case is added.</p>
  <p><b>2003.04.09</b></p>
  <p>Another test that throwed an exception was found, but already seems to be fixed
    by the 2002.09.20 work.</p>
  <p><b>2006.03.10</b></p>
  <p>Refactored the API to static methods on the Diff class to make usage simpler. The
    Self test is now using the standard Debug class.</p>
  <h3>License</h3>
  <p>This work is licensed under a <a rel="license" href="http://www.mathertel.de/License.aspx">
    BSD style license</a>.</p>
  <h3>Built-in Self-TEST</h3>
  <p>The class has now a built-in self-test, that's working without changing the code.
    If you compile the diff and debug class files together you get a exe file that's
    testing some simple diff-scenarios that where used to discover the bugs in the Version
    1 and 2 (OutOfArrayBounds typically). </p>
  <p>The compile command is: C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727\csc /target:exe
    /out:diffTest.exe /d:DEBUG /d:TRACE /d:SELFTEST Diff.cs</p>
  <p>Thanks to all the feedback and the 2 cases that did not diff correctly so I could
    provide you quality. (It was always my fault, not a problem of the algorythm).</p>
  <p>Matthias Hertel, <a href="http://www.mathertel.de/">http://www.mathertel.de</a>.</p>
  <mh:footer runat="server" />
</body>
</html>
