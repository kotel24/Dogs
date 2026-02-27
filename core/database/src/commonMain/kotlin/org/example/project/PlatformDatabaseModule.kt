package org.example.project

import org.koin.core.module.Module

internal expect fun platformDatabaseModule(fileName: String): Module
class PlatformDatabaseModule {
}
