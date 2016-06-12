<%@ Page Language="C#" AutoEventWireup="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="KEYWORDS" content="Difference Algorithm diff compare web application C# ASP.NET DotNet" />
  <meta name="Author" content="Matthias Hertel" />
  <meta name="robots" content="index,follow" />
  <link rel="copyright" href="http://www.mathertel.de/License.aspx" />
  <link href="../mathertel.css" rel="stylesheet" type="text/css" />
  <title>An O(ND) Difference Algorithm for C#</title>
  <style type="text/css">
    li { margin-top: 6px; }
  </style>
</head>
<body>
  <mh:Title runat="server" />
  <p>An C# implementation of the difference algorithm published in "An O(ND) Difference Algorithm and its Variations"
    by Eugene Myers Algorithmica Vol. 1 No. 2, 1986, p 251.</p>
  <p>This article is about comparing text files and the proven, best and most famous algorythm to identify the
    differences between them. The source code that you can find in the download implements a small class with
    a simple to use API that just does this job. You should have it in the bag of your algorythms.</p>
  <h2>Documentation and source code:</h2>
  <p><a href="DiffDoku.aspx">The documentation</a>:<br />
    This is a detailed documentation for the Diff class that explains some of the backgrounds, concepts and
    the API.</p>
  <p><a href="DiffTextLines.aspx">Diff characters</a>:<br />
    This is a step by step sample how to use the Diff class for comparing 2 strings on a character basis.</p>
  <p><a href="http://www.mathertel.de/Downloads/Start_diff.aspx">Source code</a>:<br />
    The Source code (25 kByte for the class) is availabe as a zip archive. You can also view the source code
    directly through: <a href="ViewSrc.aspx">ViewSrc</a> </p>
  <h3>Sample diff results using the older and actual versions of the Diff source code:</h3>
  <p>These links use the difference algorithm for comparing files and formatting the result as HTML. You can
    follow the evolution of it since 2002. There was no need for a bug fixing since February 2003:</p>
  <ul>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v1&newfile=Diff.cs.v2">Diff v1-&gt;v2</a></li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v2&newfile=Diff.cs.v3">Diff v2-&gt;v3</a></li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v3&newfile=Diff.cs.v4">Diff v3-&gt;v4</a></li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v4&newfile=Diff.cs.v5">Diff v4-&gt;v5</a></li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v5&newfile=Diff.cs.v6">Diff v5-&gt;v6</a><br />
      License agreement changed to a <a href="../License.aspx">BSD style license</a>.</li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v6&newfile=Diff.cs.v7">Diff v6-&gt;v7</a><br />
      Optimization of some typical text file sequences added.</li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v7&newfile=Diff.cs.v8">Diff v7-&gt;v8</a><br />
      After refactoring the 2 vector creations the algorithm seems ok on big files: 2MB files are crunched in
      a few seconds thanks to Jan Stoklasa <a href="http://www.linq.cz">http://www.linq.cz</a>.</li>
    <li><a href="DiffTest.aspx?oldfile=Diff.cs.v8&newfile=Diff.cs.v9">Diff v8-&gt;v9</a><br />
      Fixing a test case and adding a new test case from Chris.</li>
  </ul>
  <mh:Footer runat="server" />
</body>
</html>
