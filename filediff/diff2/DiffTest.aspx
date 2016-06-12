<%@ Page Language="c#" %>
<%@Import Namespace="System.IO" %>
<%@Import Namespace="my.utils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<script runat="server">
string OldFileName;
string NewFileName;

private static void WriteLine(int nr, string typ, string aText) {
  HttpContext ctx = HttpContext.Current;
  ctx.Response.Write("<tr><td>");
  if (nr >= 0)
    ctx.Response.Write((nr+1).ToString());
  else
    ctx.Response.Write("&nbsp;");
  ctx.Response.Write("<td><span xstyle='width:100%'");
  if (typ != null)
    ctx.Response.Write(" class=\"" + typ + "\"");
  aText = ctx.Server.HtmlEncode(aText).Replace("\r", "").Replace(" ", "&nbsp;");
  ctx.Response.Write(">" + aText + "</span></td></tr>\n");
}
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
    <title>File Compare Output</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style>
 BODY, TD, INPUT, TEXTAREA, SELECT 
   {FONT-FAMILY: Arial,Verdana; FONT-SIZE: 9pt; COLOR: black; }
 BODY {margin:4px; BACKGROUND-COLOR:#eaeef7;}
 TABLE {EMPTY-CELLS: show;  BORDER-COLLAPSE:collapse;}

TD {padding-right:2px; FONT-FAMILY: Monospace}
.i {COLOR:black; BACKGROUND-COLOR:#80FF80}
.d {COLOR:black; BACKGROUND-COLOR:#FF8080}
    </style>
  </head>
  <body>
    <h3 style="padding-top:8px;padding-left:40px">diff: <%=OldFileName%> &gt;&gt; <%=NewFileName%></h3>
    <table cellpadding="0" cellspacing="0" style="table-layout:fixed;padding:0">
      <colgroup>
        <col align="right" width="40">
        <col align="left" width="*">
      </colgroup>
      <tbody>

<%

  fileName = Server.MapPath("~/Diff/SampleFiles/" + OldFileName.Replace("\\", "").Replace("/", ""));
  aFile = File.OpenText(fileName);
  a = aFile.ReadToEnd();
  aFile.Close();

  fileName = Server.MapPath("~/Diff/SampleFiles/" + NewFileName.Replace("\\", "").Replace("/", ""));
  aFile = File.OpenText(fileName);
  b = aFile.ReadToEnd();
  aFile.Close();

  Diff.Item [] f = Diff.DiffText(a, b, true, true, false);
  string[]aLines = a.Split('\n');
  string[]bLines = b.Split('\n');

  int n = 0;
  for (int fdx = 0; fdx < f.Length; fdx++) {
    Diff.Item aItem = f[fdx];

    // write unchanged lines
    while ((n < aItem.StartB) && (n < bLines.Length)) {
      WriteLine(n, null, bLines[n]);
      n++;
    } // while

    // write deleted lines
    for (int m = 0; m < aItem.deletedA; m++) {
      WriteLine(-1, "d", aLines[aItem.StartA + m]);
    } // for

    // write inserted lines
    while (n < aItem.StartB + aItem.insertedB) {
      WriteLine(n, "i", bLines[n]);
      n++;
    } // while
  } // while
  
  // write rest of unchanged lines
  while (n < bLines.Length) {
    WriteLine(n, null, bLines[n]);
    n++;
  } // while

%>
      </tbody>
    </table>
    <mh:Footer runat="server" />
  </body>
</html>
