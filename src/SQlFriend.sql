/****** Object:  StoredProcedure [dbo].[ShowReview]    Script Date: 7/30/2019 12:00:44 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[ShowFriendReview]
	@Movie varchar(20),
	@User varchar(50)

AS
	/*if(not exists (select * from Review where Movie = @movie))
	begin
		return 1
	end*/
	select Comments
	from Review 
	where Movie = @Movie and UserID = @User
GO


