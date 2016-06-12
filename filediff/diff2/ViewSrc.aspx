<%@ Page AutoEventWireup="false" EnableViewState="false" Language="C#" %>
<%@ Import Namespace="System.IO" %>
<%@ Import Namespace="System.Text" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<script runat="server">
  // ViewSrc.aspx
  // Display sources ans html
  // Copyright (c) by Matthias Hertel, http://www.mathertel.de
  // This work is licensed under a BSD style license. See http://www.mathertel.de/License.aspx
  // -----
  // 05.06.2005 created by Matthias Hertel.
  // 20.12.2005 Show App_Code/Diff.cs

  private void WriteFileAsText(string fileName) {

    string srcText = File.ReadAllText(fileName);
    StringBuilder ret = new StringBuilder();

    srcText = Server.HtmlEncode(srcText);

    string[] keys = "public;private;protected;override;using;namespace;new;class;try;catch;finally;throw;if;else;for;in;foreach;while;base;this;null;string;int;long".Split(';');

    foreach (string s in srcText.Split('\n')) {
      string line = s.Replace("\r", "");
      string comment = String.Empty;

      if (line.IndexOf("//") >= 0) {
        comment = "<span style='color:green'>" + line.Substring(line.IndexOf("//")) + "</span>";
        line = line.Substring(0, line.IndexOf("//"));
      } // if
      foreach (string k in keys) {
        line = Regex.Replace(line, @"\b" + k + @"\b", "<span style='color:#0000FF'>$0</span>");
      } // foreach

      ret.Append(line);
      ret.AppendLine(comment);
    }


    TextWriter tw = new StreamWriter(Response.OutputStream, Encoding.UTF8);

    //    Server.HtmlEncode(content, tw);
    tw.Write(ret.ToString());
    tw.Flush();
    tw.Close();
  }
</script>
<%
  string fileName = Request.QueryString["file"];
  string absFileName = null;
  if (fileName == null) {
    // try referer
    fileName = Request.ServerVariables["HTTP_REFERER"];
    string appBaseUrl = Request.Url.Scheme + "://" + Request.Url.Host + HttpRuntime.AppDomainAppVirtualPath;
    if ((fileName != null) && (fileName.StartsWith(appBaseUrl, StringComparison.OrdinalIgnoreCase)))
      fileName = "~" + fileName.Substring(appBaseUrl.Length);
  } // if

  // always...
  fileName = "App_Code/Diff.cs";

  // get good filenames only (local folder)
  if ((fileName.IndexOf('$') >= 0) || (Regex.IsMatch(fileName, @"\b(COM\d|LPT\d|CON|PRN|AUX|NUL)\b", RegexOptions.IgnoreCase)))
    throw new ApplicationException("Error in filename.");

  absFileName = Server.MapPath(fileName);

  if (!absFileName.StartsWith(Request.PhysicalApplicationPath, StringComparison.InvariantCultureIgnoreCase))
    throw new ApplicationException("Can show local files only. " + fileName + " is not part of this web application.");

%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head runat="server">
  <title>An O(ND) Difference Algorithm for C# Source Code</title>
</head>

<body>
  <h1>An O(ND) Difference Algorithm for C# Source Code</h1>
  <h3>for file
    <%= Server.HtmlEncode(fileName)%>
  </h3>
  <pre class="code"><% WriteFileAsText(absFileName); %></pre>
  <mh:footer runat="server" />
</body>
</html>
