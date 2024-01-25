-- schema.sql
CREATE TABLE Broths (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    IsVegan BOOLEAN NOT NULL
);

CREATE TABLE [dbo].[Noodles] (
    [Id]      INT           IDENTITY (1, 1) NOT NULL,
    [Name]    NVARCHAR (25) NOT NULL,
    [BrothId] INT           NOT NULL,
    CONSTRAINT [PK_Noodles] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Noodles_Broths_BrothId] FOREIGN KEY ([BrothId]) REFERENCES [dbo].[Broths] ([Id]) ON DELETE CASCADE
);

GO
CREATE NONCLUSTERED INDEX [IX_Noodles_BrothId]
    ON [dbo].[Noodles]([BrothId] ASC);

CREATE TABLE [dbo].[Garnishes] (
    [Id]       INT           IDENTITY (1, 1) NOT NULL,
    [Name]     NVARCHAR (25) NOT NULL,
    [NoodleId] INT           NULL,
    CONSTRAINT [PK_Garnishes] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Garnishes_Noodles_NoodleId] FOREIGN KEY ([NoodleId]) REFERENCES [dbo].[Noodles] ([Id])
);

GO
CREATE NONCLUSTERED INDEX [IX_Garnishes_NoodleId]
    ON [dbo].[Garnishes]([NoodleId] ASC);