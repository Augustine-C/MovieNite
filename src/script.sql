USE [master]
GO
/****** Object:  Database [MovieNite_S3G7_TEST]    Script Date: 5/17/2019 2:45:41 AM ******/
CREATE DATABASE [MovieNite_S3G7_TEST]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MovieNite_S3G7_TEST', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\MovieNite_S3G7_TEST.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'MovieNite_S3G7_TEST_log', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\MovieNite_S3G7_TEST_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MovieNite_S3G7_TEST].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ARITHABORT OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET RECOVERY FULL 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET  MULTI_USER 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET DELAYED_DURABILITY = DISABLED 
GO
USE [MovieNite_S3G7_TEST]
GO
/****** Object:  User [zouh]    Script Date: 5/17/2019 2:45:42 AM ******/
CREATE USER [zouh] FOR LOGIN [zouh] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [cuiy1]    Script Date: 5/17/2019 2:45:42 AM ******/
CREATE USER [cuiy1] FOR LOGIN [cuiy1] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [zouh]
GO
ALTER ROLE [db_owner] ADD MEMBER [cuiy1]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 5/17/2019 2:45:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [varchar](10) NOT NULL,
	[EmailAddr] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[FirstName] [varchar](50) NULL,
	[PasswordHash] [varchar](50) NOT NULL,
	[PasswordSalt] [varchar](50) NOT NULL,
 CONSTRAINT [PK__Account__1788CCAC92DF904F] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Attend]    Script Date: 5/17/2019 2:45:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attend](
	[MovieEvent] [int] NOT NULL,
	[UserID] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[MovieEvent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dislikes]    Script Date: 5/17/2019 2:45:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dislikes](
	[UserID] [varchar](10) NOT NULL,
	[Movie] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[Movie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[IsFriendWith]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IsFriendWith](
	[User1] [varchar](10) NOT NULL,
	[User2] [varchar](10) NOT NULL,
	[Status] [smallint] NOT NULL,
 CONSTRAINT [PK__IsFriend__92F3DB12A374D4B2] PRIMARY KEY CLUSTERED 
(
	[User1] ASC,
	[User2] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Likes]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Likes](
	[UserID] [varchar](10) NOT NULL,
	[Movie] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[Movie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MovieEvent]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MovieEvent](
	[Location] [varchar](50) NOT NULL,
	[TimeMovieEvent] [datetime] NOT NULL,
	[Host] [varchar](10) NOT NULL,
	[EventID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_MovieEvent] PRIMARY KEY CLUSTERED 
(
	[EventID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MovieScore]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MovieScore](
	[UserID] [varchar](10) NOT NULL,
	[Movie] [varchar](20) NOT NULL,
	[Score] [float] NOT NULL,
 CONSTRAINT [PK_MovieScore] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[Movie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MovieVote]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MovieVote](
	[MovieEvent] [int] NOT NULL,
	[Movie] [varchar](20) NOT NULL,
	[UserID] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[Movie] ASC,
	[MovieEvent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[Comments] [varchar](256) NULL,
	[TimeReview] [datetime] NOT NULL,
	[UserID] [varchar](10) NOT NULL,
	[Movie] [varchar](20) NOT NULL,
 CONSTRAINT [PK__Review__0AB7CAF828FC7127] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[Movie] ASC,
	[TimeReview] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[TOP_10_Movies]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[TOP_10_Movies]
AS
SELECT        TOP (10) Movie, AVG(Score) AS Score
FROM            dbo.MovieScore
GROUP BY Movie
GO
ALTER TABLE [dbo].[Attend]  WITH CHECK ADD  CONSTRAINT [FK__Attend__MovieEve__2D27B809] FOREIGN KEY([MovieEvent])
REFERENCES [dbo].[MovieEvent] ([EventID])
GO
ALTER TABLE [dbo].[Attend] CHECK CONSTRAINT [FK__Attend__MovieEve__2D27B809]
GO
ALTER TABLE [dbo].[Attend]  WITH CHECK ADD  CONSTRAINT [FK__Attend__UserID__2C3393D0] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Attend] CHECK CONSTRAINT [FK__Attend__UserID__2C3393D0]
GO
ALTER TABLE [dbo].[Dislikes]  WITH CHECK ADD  CONSTRAINT [FK__Dislikes__UserID__182C9B23] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Dislikes] CHECK CONSTRAINT [FK__Dislikes__UserID__182C9B23]
GO
ALTER TABLE [dbo].[IsFriendWith]  WITH CHECK ADD  CONSTRAINT [FK__IsFriendW__User1__145C0A3F] FOREIGN KEY([User1])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[IsFriendWith] CHECK CONSTRAINT [FK__IsFriendW__User1__145C0A3F]
GO
ALTER TABLE [dbo].[IsFriendWith]  WITH CHECK ADD  CONSTRAINT [FK__IsFriendW__User2__15502E78] FOREIGN KEY([User2])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[IsFriendWith] CHECK CONSTRAINT [FK__IsFriendW__User2__15502E78]
GO
ALTER TABLE [dbo].[Likes]  WITH CHECK ADD  CONSTRAINT [FK__Likes__UserID__1ED998B2] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Likes] CHECK CONSTRAINT [FK__Likes__UserID__1ED998B2]
GO
ALTER TABLE [dbo].[MovieEvent]  WITH CHECK ADD  CONSTRAINT [FK__MovieEvent__Host__1BFD2C07] FOREIGN KEY([Host])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[MovieEvent] CHECK CONSTRAINT [FK__MovieEvent__Host__1BFD2C07]
GO
ALTER TABLE [dbo].[MovieScore]  WITH CHECK ADD  CONSTRAINT [FK_MovieScore_MovieScore] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[MovieScore] CHECK CONSTRAINT [FK_MovieScore_MovieScore]
GO
ALTER TABLE [dbo].[MovieVote]  WITH CHECK ADD  CONSTRAINT [FK__MovieVote__Movie__29572725] FOREIGN KEY([MovieEvent])
REFERENCES [dbo].[MovieEvent] ([EventID])
GO
ALTER TABLE [dbo].[MovieVote] CHECK CONSTRAINT [FK__MovieVote__Movie__29572725]
GO
ALTER TABLE [dbo].[MovieVote]  WITH CHECK ADD  CONSTRAINT [FK__MovieVote__UserI__286302EC] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[MovieVote] CHECK CONSTRAINT [FK__MovieVote__UserI__286302EC]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK__Review__UserID__22AA2996] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK__Review__UserID__22AA2996]
GO
/****** Object:  StoredProcedure [dbo].[add_score]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[add_score]
	-- Add the parameters for the stored procedure here
	(	@UserID varchar(10),
		@Score float,
		@Movie varchar(20)
	  )
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	--SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF(@UserID is NULL or @Score is NULL or @Movie is NULL)
	BEGIN
		print 'NULL INPUT'
		return 1
	END
	if(not exists (SELECT * from Account WHERE UserID = @UserID))
	BEGIN
		print 'UserId does not exist'
		return 2
	END

	IF(@Score < 0 or @Score>10)
	BEGIN
		print 'invalid score'
		return 3;
	END

	if(exists (select * from MovieScore where UserID = @UserID and Movie = @Movie))
	Begin
		update MovieScore
		Set Score = @Score
		where UserID = @UserID and Movie = @Movie
		return 0
	end
	
	INSERT INTO MovieScore (UserID, Movie, Score)
	VALUES(@UserID, @Movie, @Score)
	PRINT 'add score successfully' 
	return 0

END
GO
/****** Object:  StoredProcedure [dbo].[addAttend]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[addAttend]
	-- Add the parameters for the stored procedure here
	@Host varchar(10),
	@EventID int,
	@UserID varchar(10)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	IF not exists (select * from MovieEvent where Host = @Host and EventID = @EventID)
	BEGIN
		print 'invalid eventID'
		return 1
	END
	IF not exists (select * from IsFriendWith 
	where (User1=@Host and User2 = @UserID and IsFriendWith.Status = 1)
			or (User2=@Host and User1 = @UserID and IsFriendWith.Status = 1))

	BEGIN 
		print 'not friend'
		return 2
	END

	If exists (select * from Attend where UserID = @UserID and MovieEvent = @EventID)
	BEGIN 
		print 'already attend'
		return 3
	END

	INSERT INTO Attend(MovieEvent,UserID)
	VALUES (@EventID,@UserID)
	return 0
END
GO
/****** Object:  StoredProcedure [dbo].[AddFriend]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddFriend]
	@User1ID varchar(10), 
	@User2ID varchar(10)
AS

if not exists (SELECT * FROM Account where UserID = @User2ID)
begin 
	print 'invalid user'
	return 1
end

if @User1ID  = @User2ID
BEGIN
	print 'add not add yourself'
	return 2
END

Insert into dbo.IsFriendWith(User1, User2, Status)
Values (@User1ID, @User2ID, 0)
return 0

GO
/****** Object:  StoredProcedure [dbo].[AddReview]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddReview]
	@Comments varchar(256), 
	@TimeReview datetime,
	@UserID varchar(10),
	@Movie varchar(20)

AS
	if(not exists (select * from Account where UserID  = @UserID))
	begin
		print 'User does not exists'
		return 1
	end

	insert into Review (Comments,TimeReview,UserID,Movie)
	Values (@Comments, @TimeReview, @UserID, @Movie)
GO
/****** Object:  StoredProcedure [dbo].[AddUser]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddUser]
	@UserID varchar(10), 
	@Email nvarchar(50) = null,
	@LastName varchar(50) = null,
    @FirstName varchar(50) = null,
    @PasswordHash varchar(50),
	@PasswordSalt varchar(50)

AS

IF (@UserID is NULL)
BEGIN
    Print 'UserID cannot be null'
    return 1
END
if (@PasswordHash is null)
BEGIN
    Print 'PasswardHash cannot be null'
    return 1
END

if (@PasswordSalt is null)
BEGIN
    Print 'PasswardSalt cannot be null'
    return 2
END

if exists (SELECT * From Account Where UserID = @UserID)
BEGIN
    Print 'UserId is being used'
    return 3
END
Insert into dbo.Account (UserID, EmailAddr, LastName, FirstName,PasswordHash, PasswordSalt)
Values (@UserID, @Email, @LastName, @FirstName, @PasswordHash, @PasswordSalt)
return 0

GO
/****** Object:  StoredProcedure [dbo].[ApproveFriend]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ApproveFriend]
	@User1ID varchar(10), 
	@User2ID varchar(10)
AS


UPDATE IsFriendWith
SET Status = 1
WHERE User1 = @User1ID AND User2 = @User2ID

RETURN 1
GO
/****** Object:  StoredProcedure [dbo].[avg_score]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[avg_score]
	-- Add the parameters for the stored procedure here
	(
		@Movie varchar(20),
		@value float output
	  )
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	--SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF(@Movie is NULL)
	BEGIN
		print 'NULL INPUT'
		select @value = -2
		return @value;
	END
	IF(not exists (select * from MovieScore where Movie = @Movie))
	begin
		print 'no score'
		select @value = -1
		return @value;
	end
	Select @value = AVG(Score) from MovieScore where Movie =  @Movie
	return @value;
END
GO
/****** Object:  StoredProcedure [dbo].[ChangeVote]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ChangeVote] 
	-- Add the parameters for the stored procedure here
	@UserId varchar(10),
	@Movie varchar(20),
	@MovieEvent int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	if exists (SELECT * From MovieVote 
	WHERE MovieEvent = @MovieEvent
			and movie = @Movie
			and UserID = @UserId)
	BEGIN
		delete from MovieVote
		where 
			MovieEvent = @MovieEvent
		and movie = @Movie
		and UserID = @UserId
	END

	else
	begin
		insert into MovieVote(MovieEvent,Movie,UserID)
		Values(@MovieEvent,@Movie,@UserId)
	end
END
GO
/****** Object:  StoredProcedure [dbo].[checkDislikeMovie]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[checkDislikeMovie]
    @Movie varchar(20),
    @UserID varchar(10)
AS
BEGIN
    If (@Movie is null or @UserID is null)
    BEGIN
        PRINT 'NULL input'
        return 1
    END

    If not exists (Select * From Account where Account.UserID = @UserID)
    Begin
        Print 'User dose not exist'
        return 2
    END

    If exists (Select * From Dislikes where Dislikes.UserID = @UserID  and Dislikes.Movie = @Movie)
    BEGIN
        Print 'You have already liked this movie'
        return 0
    END

	return 3
END
GO
/****** Object:  StoredProcedure [dbo].[checkLikeMovie]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[checkLikeMovie]
    @Movie varchar(20),
    @UserID varchar(10)
AS
BEGIN
    If (@Movie is null or @UserID is null)
    BEGIN
        PRINT 'NULL input'
        return 1
    END

    If not exists (Select * From Account where Account.UserID = @UserID)
    Begin
        Print 'User dose not exist'
        return 2
    END

    If exists (Select * From likes where likes.UserID = @UserID  and likes.Movie = @Movie)
    BEGIN
        Print 'You have already liked this movie'
        return 0
    END

	return 3
END
GO
/****** Object:  StoredProcedure [dbo].[DisLikeMovie]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[DisLikeMovie]
    @Movie varchar(20),
    @UserID varchar(10)
AS
BEGIN
    If (@Movie is null or @UserID is null)
    BEGIN
        PRINT 'NULL input'
        return 1
    END

    If not exists (Select * From Account where Account.UserID = @UserID)
    Begin
        Print 'User dose not exist'
        return 2
    END

    If exists (Select * From Dislikes where Dislikes.UserID = @UserID and Dislikes.Movie = @Movie)
    BEGIN
        Print 'You have already disliked this movie'
        return 3
    END
	exec revertLike @UserID, @Movie
    Insert into Dislikes (UserID, Movie)
    VALUES (@UserID, @Movie)
    return 0;

	

END
GO
/****** Object:  StoredProcedure [dbo].[GetAttendEventByUser]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[GetAttendEventByUser] 
	-- Add the parameters for the stored procedure here
	@UserID varchar(10)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	If @UserID is NULL
	BEGIN 
			print 'null input'
            return 1
	END

	 If (not exists (Select * from Account where Account.UserID = @UserID))
    BEGIN
        print 'Account dose not exists'
        return 2
    END

	-- If (not exists (Select * from Attend where UserID = @UserID))
    --BEGIN
     --   print 'User dose not attend any event'
      --  return 2
    --END


    -- Insert statements for procedure here
	--DECLARE @EventID int
	--SET @EventID = (SELECT MovieEvent FROM Attend WHERE UserID = @UserID)

	SELECT EventID, Location, TimeMovieEvent, Host 
	FROM MovieEvent
	JOIN ATTEND ON Attend.MovieEvent = MovieEvent.EventID
	WHERE Attend.UserID = @UserID
END
GO
/****** Object:  StoredProcedure [dbo].[GetHostEventsbyUser]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[GetHostEventsbyUser] 
	-- Add the parameters for the stored procedure here
	@UserID varchar(10)

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	If @UserID is NULL
	BEGIN 
			print 'null input'
            return 1
	END

	 If (not exists (Select * from Account where Account.UserID = @UserID))
    BEGIN
        print 'Account dose not exists'
        return 2
    END

    -- Insert statements for procedure here
	SELECT * From MovieEvent
	WHERE MovieEvent.Host = @UserID
	return 0
END
GO
/****** Object:  StoredProcedure [dbo].[HostMovieEvent]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[HostMovieEvent]
    @Location varchar(50),
    @TimeMovieEvent Datetime,
    @Host varchar(10)
AS
BEGIN
    IF (@Location is null or @TimeMovieEvent is null or @Host is null)
        BEGIN
            print 'null input'
            return 1
        END
    If (not exists (Select * from Account where Account.UserID = @Host))
    BEGIN
        print 'Host dose not exists'
        return 2
    END
    Insert into MovieEvent ([location], [TimeMovieEvent], Host)
    Values (@Location, @TimeMovieEvent, @Host)
    return 0
END
GO
/****** Object:  StoredProcedure [dbo].[LikeMovie]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[LikeMovie]
    @Movie varchar(20),
    @UserID varchar(10)
AS
BEGIN
    If (@Movie is null or @UserID is null)
    BEGIN
        PRINT 'NULL input'
        return 1
    END

    If not exists (Select * From Account where Account.UserID = @UserID)
    Begin
        Print 'User dose not exist'
        return 2
    END

    If exists (Select * From likes where likes.UserID = @UserID and likes.Movie = @Movie)
    BEGIN
        Print 'You have already liked this movie'
        return 3
    END
	exec revertDislike @UserID, @Movie
    Insert into Likes (UserID, Movie)
    VALUES (@UserID, @Movie)
    return 0;

END
GO
/****** Object:  StoredProcedure [dbo].[ModifyUserEmail]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[ModifyUserEmail]
	@UserID varchar(10), 
	@email varchar(50)
AS

BEGIN

IF (@UserID is NULL)
BEGIN
    Print 'UserID cannot be null'
    return 1
END
if (@email is null)
BEGIN
    Print 'email cannot be null'
    return 2
END



Update dbo.Account
Set Account.EmailAddr = @email
where Account.UserID = @UserID
return 0
END

GO
/****** Object:  StoredProcedure [dbo].[ModifyUserFirstname]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[ModifyUserFirstname]
	@UserID varchar(10), 
	@Firstname varchar(50)
AS

BEGIN

IF (@UserID is NULL)
BEGIN
    Print 'UserID cannot be null'
    return 1
END
if (@Firstname is null)
BEGIN
    Print 'firstname cannot be null'
    return 2
END



Update dbo.Account
Set Account.FirstName = @Firstname
where UserID = @UserID
return 0
END

GO
/****** Object:  StoredProcedure [dbo].[ModifyUserLastname]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[ModifyUserLastname]
	@UserID varchar(10), 
	@Lastname varchar(50)
AS

BEGIN

IF (@UserID is NULL)
BEGIN
    Print 'UserID cannot be null'
    return 1
END
if (@Lastname is null)
BEGIN
    Print 'lastname cannot be null'
    return 2
END



Update dbo.Account
Set Account.LastName= @Lastname
where UserID = @UserID
return 0
END

GO
/****** Object:  StoredProcedure [dbo].[ModifyUserPassward]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ModifyUserPassward]
	@UserID varchar(10), 
    --@OriPasswordHash varchar(50),
	@PasswordHash varchar(50),
	@PasswordSalt varchar(50)

AS

IF (@UserID is NULL)
BEGIN
    Print 'UserID cannot be null'
    return 1
END
if (@PasswordHash is null)
BEGIN
    Print 'PasswordHash cannot be null'
    return 2
END

if (@PasswordSalt is null)
BEGIN
    Print 'PasswordSalt cannot be null'
    return 3
END
/*
if not exists (SELECT * From Account Where UserID = @UserID And PasswordHash = @OriPasswordHash)
BEGIN
    Print 'Information does not match'
    return 4
END
*/

Update dbo.Account
Set Account.PasswordHash = @PasswordHash,
Account.PasswordSalt = @PasswordSalt
where Account.UserID = @UserID
return 0

GO
/****** Object:  StoredProcedure [dbo].[numDisLikes]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[numDisLikes]
	-- Add the parameters for the stored procedure here
	(
		@Movie varchar(20)
	  )
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	--SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF(@Movie is NULL)
	BEGIN
		print 'NULL INPUT'
		return -2
	END
	declare @value as int
	Select @value = count(Movie) from dbo.Dislikes where Movie = @Movie
	return @value;
END
GO
/****** Object:  StoredProcedure [dbo].[numLikes]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[numLikes]
	-- Add the parameters for the stored procedure here
	(
		@Movie varchar(20)
	  )
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	--SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF(@Movie is NULL)
	BEGIN
		print 'NULL INPUT'
		return -2
	END
	declare @value as int
	Select @value = count(Movie) from dbo.Likes where Movie = @Movie
	return @value;
END
GO
/****** Object:  StoredProcedure [dbo].[RejectFriend]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[RejectFriend]
	@User1ID varchar(10), 
	@User2ID varchar(10)
AS


DELETE FROM IsFriendWith
WHERE User1 = @User1ID AND User2 = @User2ID

RETURN 1
GO
/****** Object:  StoredProcedure [dbo].[revertDislike]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[revertDislike]
	@UserID varchar(10),
	@Movie varchar(20)
as

if (not exists (select * from Dislikes where UserID = @UserID and Movie = @Movie))
Begin
	return 1
end

DELETE from Dislikes where UserID = @UserID and Movie = @Movie
return 0
GO
/****** Object:  StoredProcedure [dbo].[revertLike]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[revertLike]
	@UserID varchar(10),
	@Movie varchar(20)
as

if (not exists (select * from Likes where UserID = @UserID and Movie = @Movie))
Begin
	return 1
end

DELETE from Likes where UserID = @UserID and Movie = @Movie
return 0
GO
/****** Object:  StoredProcedure [dbo].[ShowDislikedMovies]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowDislikedMovies] 
	-- Add the parameters for the stored procedure here
	@id varchar(10)
AS
BEGIN
	IF (@id is NULL)
	BEGIN
		Print 'UserID cannot be null'
		return 1
	END
	Select Movie from Dislikes where UserID = @id;
	return 0;

END
GO
/****** Object:  StoredProcedure [dbo].[ShowLikedMovies]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ShowLikedMovies] 
	-- Add the parameters for the stored procedure here
	@id varchar(10)
AS
BEGIN
	IF (@id is NULL)
	BEGIN
		Print 'UserID cannot be null'
		return 1
	END
	Select Movie from Likes where UserID = @id;
	return 0;

END
GO
/****** Object:  StoredProcedure [dbo].[ShowLikeMovie]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ShowLikeMovie]
    @UserID varchar(10)
AS
BEGIN

    If not exists (Select * From Account where Account.UserID = @UserID)
    Begin
        Print 'User dose not exist'
        return 1
    END

    Select likes.Movie From likes where likes.UserID = @UserID
    return 0;

END
GO
/****** Object:  StoredProcedure [dbo].[ShowPersonalInformation]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ShowPersonalInformation] 
	-- Add the parameters for the stored procedure here
	@userID varchar(10)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SELECT FirstName, LastName, EmailAddr
	FROM Account
	WHERE UserID = @userID
END
GO
/****** Object:  StoredProcedure [dbo].[ShowReview]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ShowReview]
	@Movie varchar(20)

AS
	/*if(not exists (select * from Review where Movie = @movie))
	begin
		return 1
	end*/
	select Comments
	from Review 
	where Movie = @Movie
GO
/****** Object:  StoredProcedure [dbo].[ShowVotesByUser]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ShowVotesByUser] 
	-- Add the parameters for the stored procedure here
	@UserID varchar(10),
	@MovieEvent int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT  Movie , COUNT(UserID) as Votes FROM MovieVote
	WHere MovieEvent = @MovieEvent 
	and UserID = @UserID
	group by MovieEvent,Movie
END
GO
/****** Object:  StoredProcedure [dbo].[ShowVotesOnEvent]    Script Date: 5/17/2019 2:45:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ShowVotesOnEvent] 
	-- Add the parameters for the stored procedure here
	--@Movie varchar(20),
	@MovieEvent int 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	

    -- Insert statements for procedure here
	SELECT  Movie , COUNT(UserID) as Votes FROM MovieVote
	WHere MovieEvent = @MovieEvent 
	group by MovieEvent,Movie
END
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Review"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 1
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 12
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'TOP_10_Movies'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'TOP_10_Movies'
GO
USE [master]
GO
ALTER DATABASE [MovieNite_S3G7_TEST] SET  READ_WRITE 
GO
