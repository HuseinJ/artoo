@import com.hjusic.artoo.model.Notification
@import com.hjusic.artoo.email.formatter.LinkFormatter
@param Notification notification

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; padding: 0; }
    .container { max-width: 600px; margin: 40px auto; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .header { background: #1a1a2e; color: white; padding: 24px 32px; }
    .body { padding: 32px; color: #333; line-height: 1.6; }
    .footer { padding: 16px 32px; font-size: 12px; color: #999; border-top: 1px solid #eee; }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <h2 style="margin:0;">Notification: ${notification.subject()}</h2>
    </div>
    <div class="body">
      <p>$unsafe{LinkFormatter.linkify(notification.content())}</p>
    </div>
    <div class="footer">
      This message was sent to ${notification.recipient()}
    </div>
  </div>
</body>
</html>