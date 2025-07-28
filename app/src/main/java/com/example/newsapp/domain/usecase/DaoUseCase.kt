package com.example.newsapp.domain.usecase



data class DaoUseCase (
    val upsertUseCase: UpsertUseCase,
    val deleteUseCase: DeleteUseCase,
    val getBookMarkUseCase: GetBookMarkUseCase,
    val selectArticleUseCase: SelectArticleUseCase
)
