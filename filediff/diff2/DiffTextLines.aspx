<%@ Page Language="c#" %>
<%@ Import Namespace="System.IO" %>
<%@ Import Namespace="my.utils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<script runat="server">
  string a_line, b_line;
  int[] a_codes, b_codes;

  string OldFileName;
  string NewFileName;

  Diff.Item[] diffs;

  protected override void OnLoadComplete(EventArgs e) {
    base.OnLoadComplete(e);

    a_line = this.Request["a"];
    b_line = this.Request["b"];

    if (a_line == null)
      a_line = "Default Text For Line A.";
    if (b_line == null)
      b_line = "Default textline for line B.";

    a_codes = DiffCharCodes(a_line, false);
    b_codes = DiffCharCodes(b_line, false);

    diffs = Diff.DiffInt(a_codes, b_codes);
  }



  /// <summary>
  /// This function converts all textlines of the text into unique numbers for every unique textline
  /// so further work can work only with simple numbers.
  /// </summary>
  /// <param name="aText">the input text</param>
  /// <param name="h">This extern initialized hashtable is used for storing all ever used textlines.</param>
  /// <param name="trimSpace">ignore leading and trailing space characters</param>
  /// <returns>a array of integers.</returns>
  private static int[] DiffCharCodes(string aText, bool ignoreCase) {
    int[] Codes;

    if (ignoreCase)
      aText = aText.ToUpperInvariant();

    Codes = new int[aText.Length];

    for (int n = 0; n < aText.Length; n++)
      Codes[n] = (int)aText[n];

    return (Codes);
  } // DiffCharCodes


  // write the charCodes to response-stream
  private void WriteCodes(int[] Codes) {

    for (int n = 0; n < Codes.Length; n++) {
      if (n > 0)
        this.Response.Write("&nbsp;");
      this.Response.Write(Codes[n].ToString("x"));
    } // for
  } // WriteCodes

  private void DumpDiffs() {
    this.Response.Write("The diff result has " + diffs.Length.ToString() + "items.</br>");
    for (int n = 0; n < diffs.Length; n++) {
      Diff.Item it = diffs[n];
      if (n > 0)
        this.Response.Write("<br />");
      this.Response.Write(String.Format("StartA={0}, StartB={1}, deletedA={2}, insertedB={3}",
        it.StartA, it.StartB, it.deletedA, it.insertedB));
    }
  }

  private void WriteResult() {
    int pos = 0;
    for (int n = 0; n < diffs.Length; n++) {
      Diff.Item it = diffs[n];

      // write unchanged chars
      while ((pos < it.StartB) && (pos < b_line.Length)) {
        this.Response.Write(b_line[pos]);
        pos++;
      } // while

      // write deleted chars
      if (it.deletedA > 0) {
        this.Response.Write("<span class='cd'>");
        for (int m = 0; m < it.deletedA; m++) {
          this.Response.Write(a_line[it.StartA + m]);
        } // for
        this.Response.Write("</span>");
      }

      // write inserted chars
      if (pos < it.StartB + it.insertedB) {
        this.Response.Write("<span class='ci'>");
        while (pos < it.StartB + it.insertedB) {
          this.Response.Write(b_line[pos]);
          pos++;
        } // while
        this.Response.Write("</span>");
      } // if
    } // while

    // write rest of unchanged chars
    while (pos < b_line.Length) {
      this.Response.Write(b_line[pos]);
      pos++;
    } // while
  } // WriteResult
</script>
<%
  OldFileName = "Diff.cs.v1";
  NewFileName = "Diff.cs.v4";

  if (Request.QueryString["oldfile"] != null)
    OldFileName = Request.QueryString["oldfile"];
  if (Request.QueryString["newfile"] != null)
    NewFileName = Request.QueryString["newfile"];

  string a, b;
  string fileName;
  StreamReader aFile;
%>
<html>

<head runat="server">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link href="../mathertel.css" rel="stylesheet" type="text/css" />
  <title>Compare 2 textlines on character basis</title>
  <style type="text/css">
.ci {COLOR:black; BACKGROUND-COLOR:#80FF80}
.cd {COLOR:black; BACKGROUND-COLOR:#FF8080}
    </style>
</head>

<body>
  <mh:Title ID="Title1" runat="server" />
  <p>This page contains a step by step sample to show how to use the <a href="DiffDoku.aspx">Diff class</a> for comparing the characters
    of 2 strings.</p>
  <p>For more information about Diff class see the <a href="../Diff/">main page</a>.</p>
  <blockquote>
    <p>This page can be used with 2 URL parameters (a and b) to test this implementation:</p>
    <p>Current parameters:</p>
    <pre>Text a = "<% =a_line%>"
Text b = "<% =b_line%>"</pre>
  </blockquote>
  <ajax:Part runat="server">
    <h3>1. Parameter Preparation</h3>
    <p>Before the algorithm can be used the 2 input string must be converted into the datatype that is used by the algorithm: a int
      Array.</p>
    <p>Because we compare on a character basis this task is very easy to complete by using the character code of each char. This is
      done by the DiffCharCodes:</p>
    <pre class="code">private static int[] DiffCharCodes(string aText, bool ignoreCase) {
  int[] Codes;

  if (ignoreCase)
    aText = aText.ToUpperInvariant();

  Codes = new int[aText.Length];

  for (int n = 0; n < aText.Length; n++)
    Codes[n] = (int)aText[n];

  return (Codes);
} // DiffCharCodes
</pre>
    <blockquote>
      <p>The codes for the 2 textlines are:</p>
      <pre>a_codes = <% WriteCodes(a_codes); %>
b_codes = <% WriteCodes(b_codes); %></pre>
    </blockquote>
  </ajax:Part>
  <ajax:Part ID="Part1" runat="server">
    <h3>2. Calling the Diff Algorithm</h3>
    <p>The main entry point for the Algorithm is the LCS function that can take 2 int[] parameters and will return an array Diff.Item
      structures that are describing the difference details as identical, inserted or deleted subarrays.</p>
    <pre class="code">Diff.Item[] diffs = Diff.DiffInt(a_codes, b_codes);</pre>
    <p>Here is a dump of the actual content of this structure:</p>
    <blockquote>
      <% DumpDiffs(); %>
    </blockquote>
  </ajax:Part>
  <ajax:Part ID="Part2" runat="server">
    <h3>3. Formatting the result</h3>
    <p>Now we can use the original data together with the result items and generate a intuitive readable form of the result:</p>
    <pre class="code">int pos = 0;
for (int n = 0; n < diffs.Length; n++) {
  Diff.Item it = diffs[n];

  // write unchanged chars
  while ((pos < it.StartB) && (pos < b_line.Length)) {
    this.Response.Write(b_line[pos]);
    pos++;
  } // while

  // write deleted chars
  if (it.deletedA > 0) {
    this.Response.Write("&lt;span class='cd'&gt;");
    for (int m = 0; m < it.deletedA; m++) {
      this.Response.Write(a_line[it.StartA + m]);
    } // for
    this.Response.Write("&lt;/span&gt;");
  }

  // write inserted chars
  if (pos < it.StartB + it.insertedB) {
    this.Response.Write("&lt;span class='ci'&gt;");
    while (pos < it.StartB + it.insertedB) {
      this.Response.Write(b_line[pos]);
      pos++;
    } // while
    this.Response.Write("&lt;/span&gt;");
  } // if
} // while

// write rest of unchanged chars
while (pos < b_line.Length) {
  this.Response.Write(b_line[pos]);
  pos++;
} // while</pre>
    <p>And here is the formatted result:</p>
    <blockquote>
      <pre style="font-size: larger"><% WriteResult(); %></pre>
    </blockquote>
  </ajax:Part>
  <ajax:Part runat="server">
    <p>Here are some alternative test-cases:</p>
    <blockquote><a href="../Diff/DiffTextLines.aspx?a=This is a big thing.&b=This is a small thing.">a=This is a big thing.&amp;b=This
      is a small thing.</a><br />
      <a href="../Diff/DiffTextLines.aspx?a=identical&b=different">a=identical&amp;b=different</a><br />
    </blockquote>
  </ajax:Part>
  <mh:Footer runat="server" />
</body>
</html>
